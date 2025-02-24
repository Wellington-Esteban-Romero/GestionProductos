package org.gestion.productos.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.gestion.productos.models.Producto;
import org.gestion.productos.services.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/productos/buscar")
public class ProductoBuscarServlet extends HttpServlet {

    @Inject
    private LoginService loginService;

    @Inject
    private ProductoService productoService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String buscarNombre = req.getParameter("buscarNombre") != null ? req.getParameter("buscarNombre") : "";
        Long precioMin;
        try {
            precioMin = Long.parseLong(req.getParameter("precioMin"));
        } catch (NumberFormatException e) {
            precioMin = 0L;
        }

        Long precioMax;
        try {
            precioMax = Long.parseLong(req.getParameter("precioMax"));
        } catch (NumberFormatException e) {
            precioMax = Long.MAX_VALUE;
        }

        Optional<String> username = loginService.getUsername(req);
        req.setAttribute("username", username);
        req.setAttribute("title", req.getAttribute("title") + " - Listado de productos");

        if (buscarNombre.isEmpty() && precioMin.equals(0L) && precioMax.equals(Long.MAX_VALUE)) {
            resp.sendRedirect(req.getContextPath() +"/productos");
        } else {
            req.getServletContext().log(buscarNombre + " " + precioMin + " " + precioMax);
            List<Producto> productosEncontrados = productoService.buscarProductos(buscarNombre, precioMin, precioMax);
            req.getServletContext().log(productosEncontrados+"");
            req.setAttribute("productos", productosEncontrados);
            getServletContext().getRequestDispatcher("/listar.jsp").forward(req, resp);
        }
    }
}
