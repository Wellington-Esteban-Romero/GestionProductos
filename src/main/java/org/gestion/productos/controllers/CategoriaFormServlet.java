package org.gestion.productos.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.gestion.productos.models.Categoria;
import org.gestion.productos.models.Producto;
import org.gestion.productos.services.ProductoService;
import org.gestion.productos.utils.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/categorias/form") //adaptar lo para categoria
public class CategoriaFormServlet extends HttpServlet {

    @Inject
    private ProductoService productoService;

    @Inject
    private Utils utils;

    String nombreAnterior;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long id = utils.parseLong(req.getParameter("id"));

        Categoria categoria = new Categoria();
        if (id > 0) {
            Optional<Categoria> categoriaOptional = productoService.porIdCategoria(id);
            if (categoriaOptional.isPresent()) {
                categoria = categoriaOptional.get();
            }
        }

        nombreAnterior = categoria.getNombre();

        req.setAttribute("title", req.getAttribute("title") + " - Formulario categorías");
        req.setAttribute("categoria", categoria);
        getServletContext().getRequestDispatcher("/formCategoria.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String nombre = req.getParameter("nombre");
        String descripcion = req.getParameter("descripcion");
        long id = utils.parseLong(req.getParameter("id"));
        double precio = utils.parseDouble(req.getParameter("precio"));
        int stock = utils.parseInt(req.getParameter("stock"));
        long categoria_id = utils.parseLong(req.getParameter("categoria"));
        LocalDate fecha_registro = utils.parseFecha(req.getParameter("fecha_registro"));

        Part filePart = req.getPart("imagen");
        String fileName = null;
        if (filePart != null && filePart.getSize() > 0) {
            fileName = Path.of(filePart.getSubmittedFileName()).getFileName().toString();
            String uploadDir = getServletContext().getRealPath("/imagenes");
            System.out.println("***********uploadDir:" + uploadDir);
            Path filePath = Path.of(uploadDir, fileName);
            Files.createDirectories(filePath.getParent());
            System.out.println("***********FilePath:" + filePath.getParent());
            Files.copy(filePart.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        }

        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        producto.setStock(stock);
        producto.setFechaRegistro(fecha_registro);
        producto.setImagen(fileName);

        Categoria categoria = new Categoria();
        categoria.setId(categoria_id);
        producto.setCategoria(categoria);

        Map<String, String> errores = validar(producto);
        if (errores.isEmpty()) {
            if (id > 0) {
                producto.setId(id);
            }
            productoService.guardar(producto);
            resp.sendRedirect(req.getContextPath() + "/productos");
        } else {
            req.setAttribute("errores", errores);
            req.setAttribute("producto", producto);
            req.setAttribute("categorias", productoService.listarCategorias());
            req.setAttribute("title", req.getAttribute("title") + " - Formulario productos");
            getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
        }
    }

    private Map<String, String> validar(Producto producto) {
        Map<String, String> errores = new HashMap<>();
        if (producto.getNombre() == null || producto.getNombre().isEmpty()) {
            errores.put("nombre", "El nombre es obligatorio!");
        } else if (!producto.getNombre().equals(nombreAnterior) && productoService.existe(producto.getNombre())) {
            errores.put("nombre", "El nombre ya existe!");
        }
        if (producto.getFechaRegistro() == null) {
            errores.put("fecha_registro", "La fecha del registro es obligatorio!");
        }
        if (producto.getPrecio() == 0.0) {
            errores.put("precio", "El precio es obligatorio!");
        }
        if (producto.getStock() == 0) {
            errores.put("stock", "El stock es obligatorio!");
        }
        if (producto.getCategoria().getId().equals(0L)) {
            errores.put("categoria", "La categoría es obligatoria!");
        }
        return errores;
    }
}
