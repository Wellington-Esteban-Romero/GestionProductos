package org.gestion.productos.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.gestion.productos.models.Producto;
import org.gestion.productos.services.LoginService;
import org.gestion.productos.services.ProductoService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productos.html", "/productos"})
public class ProductoServlet extends HttpServlet {

    private static final int TAMANIO_PAGINA = 10;

    @Inject
    private ProductoService productoService;

    @Inject
    private LoginService loginService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pagina;
        try {
            pagina = Integer.parseInt(req.getParameter("pagina"));
        } catch (NumberFormatException e) {
            pagina = 0;
        }
        List<Producto> productos = productoService.obtenerProductos(pagina, TAMANIO_PAGINA);
        int totalProductos = productoService.contarProductos();
        int totalPaginas = (int) Math.ceil((double) totalProductos / TAMANIO_PAGINA);
        Optional<String> username = loginService.getUsername(req);

        req.setAttribute("title", req.getAttribute("title") + " - Listado de productos");
        req.setAttribute("productos", productos);
        req.setAttribute("pagina", pagina);
        req.setAttribute("totalPaginas", totalPaginas);
        req.setAttribute("username", username);

        getServletContext().getRequestDispatcher("/listar.jsp").forward(req, resp);
    }
}
