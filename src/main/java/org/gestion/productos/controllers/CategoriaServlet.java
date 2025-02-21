package org.gestion.productos.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.gestion.productos.models.Categoria;
import org.gestion.productos.models.Producto;
import org.gestion.productos.services.ProductoService;
import org.gestion.productos.services.ProductoServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet({"/categorias.html", "/categorias"})
public class CategoriaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");

        ProductoService productoService = new ProductoServiceJdbcImpl(conn);
        List<Categoria> categorias = productoService.listarCategorias();

        req.setAttribute("categorias", categorias);

        getServletContext().getRequestDispatcher("/listar_categorias.jsp").forward(req, resp);
    }
}
