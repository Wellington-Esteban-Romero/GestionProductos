package org.gestion.productos.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.gestion.productos.models.Categoria;
import org.gestion.productos.services.ProductoService;

import java.io.IOException;
import java.util.List;

@WebServlet({"/categorias.html", "/categorias"})
public class CategoriaServlet extends HttpServlet {

    @Inject
    private ProductoService productoService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Categoria> categorias = productoService.listarCategorias();

        req.setAttribute("categorias", categorias);
        req.setAttribute("title", req.getAttribute("title") + " - Lista categorías");

        getServletContext().getRequestDispatcher("/listar_categorias.jsp").forward(req, resp);
    }
}
