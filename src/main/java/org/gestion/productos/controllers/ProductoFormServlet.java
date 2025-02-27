package org.gestion.productos.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.gestion.productos.models.Categoria;
import org.gestion.productos.models.Producto;
import org.gestion.productos.services.ProductoService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/productos/form")
public class ProductoFormServlet extends HttpServlet {

    @Inject
    private ProductoService productoService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }
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

        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        double precio;
        try {
            precio = Double.parseDouble(req.getParameter("precio"));
            System.out.println("Precio: " +precio);
        } catch (NumberFormatException e) {
            precio = 0.0;
        }
        int stock;
        try {
            stock = Integer.parseInt(req.getParameter("stock"));
        } catch (NumberFormatException e) {
            stock = 0;
        }

        String fechaRegistro = req.getParameter("fecha_registro");
        long categoria_id;
        try {
            categoria_id = Long.parseLong(req.getParameter("categoria"));
        } catch (NumberFormatException e) {
            categoria_id = 0L;
        }

        LocalDate fecha_registro;
        try {
            fecha_registro = LocalDate.parse(fechaRegistro, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            fecha_registro = null;
        }
        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        producto.setStock(stock);
        producto.setFechaRegistro(fecha_registro);

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
            //req.setAttribute("producto", req.getAttribute("inputGroupFile04"));
            getServletContext().log(req.getAttribute("inputGroupFile04") + "sasasaweq ");
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
}
