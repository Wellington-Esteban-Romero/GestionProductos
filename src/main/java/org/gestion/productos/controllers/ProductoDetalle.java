package org.gestion.productos.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.gestion.productos.models.Producto;
import org.gestion.productos.services.ProductoService;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/productos/detalles")
public class ProductoDetalle extends HttpServlet {

    @Inject
    private ProductoService productoService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id") != null ? req.getParameter("id") : "0");
        Optional<Producto> producto = productoService.porId(id);
        if (producto.isPresent()) {
            req.setAttribute("producto", producto.get());
            req.setAttribute("title", req.getAttribute("title") + " - Detalle Producto");
            getServletContext().getRequestDispatcher("/detalle.jsp").forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No se encontró el producto con el id " + id);
        }
    }
}
