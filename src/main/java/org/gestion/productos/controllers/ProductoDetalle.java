package org.gestion.productos.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.gestion.productos.models.Producto;
import org.gestion.productos.services.ProductoService;
import org.gestion.productos.services.ProductoServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/productos/detalles")
public class ProductoDetalle extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        Long id = Long.parseLong(req.getParameter("id") != null ? req.getParameter("id") : "0");
        ProductoService productoService = new ProductoServiceJdbcImpl(conn);
        Optional<Producto> producto = productoService.porId(id);
        if (producto.isPresent()) {
            req.setAttribute("producto", producto.get());
            req.setAttribute("title", req.getParameter("title") + " Detalle Producto");
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No se encontró el producto con el id " + id);
        }
    }
}
