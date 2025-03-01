package org.gestion.productos.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.gestion.productos.models.Categoria;
import org.gestion.productos.services.ProductoService;
import org.gestion.productos.utils.Utils;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/categorias/eliminar")
public class CategoriaEliminarServlet extends HttpServlet {

    @Inject
    private ProductoService productoService;

    @Inject
    private Utils utils;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long id = utils.parseLong(req.getParameter("id"));
        int activarId = utils.parseInt(req.getParameter("active"));

        if (id > 0) {
            Optional<Categoria> categoriaOptional = productoService.porIdCategoria(id);
            if (categoriaOptional.isPresent()) {
                if (activarId == 1) {
                    productoService.activar(id);
                } else {
                    productoService.desactivar(id);
                }
                resp.sendRedirect(req.getContextPath() + "/categorias");
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe la categoría");
            }

        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Error id, la categoría debe existir");
        }
    }
}
