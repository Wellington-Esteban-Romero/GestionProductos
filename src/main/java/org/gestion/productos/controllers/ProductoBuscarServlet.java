package org.gestion.productos.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.gestion.productos.dto.ProductoFiltroDTO;
import org.gestion.productos.models.Producto;
import org.gestion.productos.services.*;
import org.gestion.productos.utils.Utils;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@WebServlet("/productos/buscar")
public class ProductoBuscarServlet extends HttpServlet {

    @Inject
    private LoginService loginService;

    @Inject
    private ProductoService productoService;

    @Inject
    private Utils utils;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String buscarNombre = req.getParameter("buscarNombre") != null ? req.getParameter("buscarNombre") : "";
        String tipo = req.getParameter("tipo") != null ? req.getParameter("tipo") : "";
        long precioMin = utils.parseLong(req.getParameter("precioMin"));
        long precioMax = utils.parseLongMax(req.getParameter("precioMax"));
        LocalDate fecha_inicio = utils.parseFecha(req.getParameter("fecha_inicio"));
        LocalDate fecha_fin = utils.parseFecha(req.getParameter("fecha_fin"));

        ProductoFiltroDTO filtro = new ProductoFiltroDTO(buscarNombre, tipo, precioMin, precioMax, fecha_inicio, fecha_fin);
        req.getServletContext().log("Filtro de búsqueda: " + filtro);

        Optional<String> username = loginService.getUsername(req);
        req.setAttribute("username", username);
        req.setAttribute("title", req.getAttribute("title") + " - Listado de productos");

        if (filtro.getNombre().isEmpty() && filtro.getTipo().isEmpty() && filtro.getPrecioMin().equals(0L)
                && filtro.getPrecioMax().equals(Long.MAX_VALUE) && fecha_inicio == null && fecha_fin == null) {
            resp.sendRedirect(req.getContextPath() + "/productos");
        } else {
            List<Producto> productosEncontrados = productoService.buscarProductos(filtro);
            req.setAttribute("productos", productosEncontrados);
            getServletContext().getRequestDispatcher("/listar.jsp").forward(req, resp);
        }
    }
}
