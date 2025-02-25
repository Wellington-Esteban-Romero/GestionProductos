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

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
        String tipo = req.getParameter("tipo") != null ? req.getParameter("tipo") : "";

        long precioMin;
        try {
            precioMin = Long.parseLong(req.getParameter("precioMin"));
        } catch (NumberFormatException e) {
            precioMin = 0L;
        }

        long precioMax;
        try {
            precioMax = Long.parseLong(req.getParameter("precioMax"));
        } catch (NumberFormatException e) {
            precioMax = Long.MAX_VALUE;
        }

        LocalDate fecha_inicio;
        try {
            fecha_inicio = LocalDate.parse(req.getParameter("fecha_inicio"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            fecha_inicio = null;
        }

        LocalDate fecha_fin;
        try {
            fecha_fin = LocalDate.parse(req.getParameter("fecha_fin"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            fecha_fin = null;
        }

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
