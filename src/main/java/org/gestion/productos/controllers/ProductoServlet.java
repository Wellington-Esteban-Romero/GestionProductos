package org.gestion.productos.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.gestion.productos.models.Producto;
import org.gestion.productos.models.Usuario;
import org.gestion.productos.services.LoginService;
import org.gestion.productos.services.ProductoService;
import org.gestion.productos.utils.Constantes;
import org.gestion.productos.utils.Utils;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productos.html", "/productos"})
public class ProductoServlet extends HttpServlet {

    @Inject
    private ProductoService productoService;

    @Inject
    private LoginService loginService;

    @Inject
    private Utils utils;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pagina = utils.parseInt(req.getParameter("pagina"));
        List<Producto> productos = productoService.obtenerProductos(pagina, Constantes.TAMANIO_PAGINA);
        int totalProductos = productoService.contarProductos();
        int totalPaginas = (int) Math.ceil((double) totalProductos / Constantes.TAMANIO_PAGINA);
        Optional<Usuario> usuarioOptional = loginService.getUsername(req);

        req.setAttribute("title", req.getAttribute("title") + " - Listado de productos");
        req.setAttribute("productos", productos);
        req.setAttribute("pagina", pagina);
        req.setAttribute("totalPaginas", totalPaginas);
        req.setAttribute("username", usuarioOptional);

        getServletContext().getRequestDispatcher("/listar.jsp").forward(req, resp);
    }
}
