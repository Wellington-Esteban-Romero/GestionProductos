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
import java.util.List;

@WebServlet("/inicio.jsp")
public class InicioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = (Connection) req.getAttribute("conn");

        ProductoService service = new ProductoServiceJdbcImpl(conn);
        int totalProductos = service.obtenerTotalProductos();
        List<Producto> productos = service.obtenerUltimosProductos();

        req.setAttribute("totalProductos", totalProductos);
        req.setAttribute("ultimosProductos", productos);

        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
