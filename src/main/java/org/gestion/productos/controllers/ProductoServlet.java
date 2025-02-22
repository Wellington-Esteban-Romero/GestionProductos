package org.gestion.productos.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.gestion.productos.models.Producto;
import org.gestion.productos.services.LoginService;
import org.gestion.productos.services.LoginServiceSessionImpl;
import org.gestion.productos.services.ProductoService;
import org.gestion.productos.services.ProductoServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productos.html", "/productos"})
public class ProductoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService productoService = new ProductoServiceJdbcImpl(conn);
        LoginService loginService = new LoginServiceSessionImpl();
        List<Producto> productos = productoService.getProductos();
        Optional<String> username = loginService.getUsername(req);

        req.setAttribute("title", req.getAttribute("title") + " - Listado de productos");
        req.setAttribute("productos", productos);
        req.setAttribute("username", username);

        getServletContext().getRequestDispatcher("/listar.jsp").forward(req, resp);
    }
}
