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

@WebServlet("/productos/buscar")
public class BuscarProductoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        String nombre = req.getParameter("nombre") != null ? req.getParameter("nombre") : "";
        ProductoService productoService = new ProductoServiceJdbcImpl(conn);
        LoginService loginService = new LoginServiceSessionImpl();
        Optional<String> username = loginService.getUsername(req);

        if (nombre.isEmpty()) {
            List<Producto> productos = productoService.getProductos();
            req.setAttribute("productos", productos);
            req.setAttribute("username", username);
            getServletContext().getRequestDispatcher("/listar.jsp").forward(req, resp);
        } else {
            Optional<Producto> productoEncontrado = productoService.buscarProductoPorNombre(nombre);
            if (productoEncontrado.isPresent()) {
                req.setAttribute("productos", List.of(productoEncontrado.get()));
                req.setAttribute("username", username);
                getServletContext().getRequestDispatcher("/listar.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No se encontró el producto " + nombre);
            }
        }
    }
}
