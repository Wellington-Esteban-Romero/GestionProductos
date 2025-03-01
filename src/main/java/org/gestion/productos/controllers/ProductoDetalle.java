package org.gestion.productos.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.gestion.productos.models.Producto;
import org.gestion.productos.services.ProductoService;
import org.gestion.productos.utils.Utils;

import java.io.IOException;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@WebServlet("/productos/detalles")
public class ProductoDetalle extends HttpServlet {

    @Inject
    private ProductoService productoService;

    @Inject
    private Utils utils;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long id = utils.parseLong(req.getParameter("id"));
        Optional<Producto> producto = productoService.porId(id);
        if (producto.isPresent()) {
            req.setAttribute("producto", producto.get());
            req.setAttribute("fechaRegistro", Date.from(producto.get().getFechaRegistro().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            req.setAttribute("fechaModificacion", Date.from(producto.get().getFechaRegistro().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            req.setAttribute("title", req.getAttribute("title") + " - Detalle Producto");
            getServletContext().getRequestDispatcher("/detalle.jsp").forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No se encontró el producto con el id " + id);
        }
    }
}
