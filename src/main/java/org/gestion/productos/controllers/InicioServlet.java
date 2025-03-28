package org.gestion.productos.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.gestion.productos.models.ReporteMensual;
import org.gestion.productos.services.ProductoService;

import java.io.IOException;
import java.util.List;

@WebServlet("/inicio.jsp")
public class InicioServlet extends HttpServlet {

    @Inject
    private ProductoService productoService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int totalProductos = productoService.obtenerTotalProductos();
        List<ReporteMensual> productosPorMes = productoService.obtenerProductosAgregadosPorMes();
        List<ReporteMensual> productosVendidosPorMes = productoService.obtenerProductosAgregadosPorMes();

        req.setAttribute("totalProductos", totalProductos);
        req.setAttribute("productosPorMes", productosPorMes);
        req.setAttribute("productosVendidosPorMes", productosVendidosPorMes);

        req.setAttribute("title", req.getAttribute("title") + " - Dashboard");
        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
