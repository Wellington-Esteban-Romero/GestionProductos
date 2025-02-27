package org.gestion.productos.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.gestion.productos.models.Categoria;
import org.gestion.productos.models.Producto;
import org.gestion.productos.services.ProductoService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/productos/form")
@MultipartConfig(maxFileSize = 10 * 1024 * 1024)
public class ProductoFormServlet extends HttpServlet {

    @Inject
    private ProductoService productoService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long id =  parseLong(req.getParameter("id"));

        Producto producto = new Producto();
        producto.setCategoria(new Categoria());
        if (id > 0) {
            Optional<Producto> productoOptional = productoService.porId(id);
            if (productoOptional.isPresent()) {
                producto = productoOptional.get();
            }
        }

        req.setAttribute("title", req.getAttribute("title") + " - Formulario productos");
        req.setAttribute("producto", producto);
        req.setAttribute("categorias", productoService.listarCategorias());
        getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String nombre = req.getParameter("nombre");
        String descripcion = req.getParameter("descripcion");
        long id =  parseLong(req.getParameter("id"));
        double precio = parseDouble(req.getParameter("precio"));
        int stock = parseInt(req.getParameter("stock"));
        long categoria_id = parseLong(req.getParameter("categoria"));
        LocalDate fecha_registro = parseFecha(req.getParameter("fecha_registro"));

        Part filePart = req.getPart("imagen");
        String fileName = null;
        if (filePart != null && filePart.getSize() > 0) {
            fileName = Path.of(filePart.getSubmittedFileName()).getFileName().toString();
            String uploadDir = getServletContext().getRealPath("/imagenes");
            System.out.println("***********uploadDir:"+uploadDir);
            Path filePath = Path.of(uploadDir, fileName);
            Files.createDirectories(filePath.getParent());
            System.out.println("***********FilePath:"+filePath.getParent());
            Files.copy(filePart.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        }

        System.out.println(fileName);

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

    private double parseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    private int parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private long parseLong(String value) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            return 0L;
        }
    }

    private LocalDate parseFecha(String value) {
        try {
            return LocalDate.parse(value, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
