package org.gestion.productos.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.gestion.productos.models.Carro;
import org.gestion.productos.models.ItemCarro;
import org.gestion.productos.models.Producto;
import org.gestion.productos.services.ProductoService;
import org.gestion.productos.services.ProductoServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/carro/agregar")
public class AgregarCarroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        Long id = Long.parseLong(req.getParameter("id"));
        ProductoService productoService = new ProductoServiceJdbcImpl(conn);
        Optional<Producto> producto = productoService.porId(id);

        if (producto.isPresent()) {
            ItemCarro itemCarro = new ItemCarro(1, producto.get());
            Carro carro;
            HttpSession session = req.getSession();
            carro = (Carro) session.getAttribute("carro");
            carro.addItem(itemCarro);
        }
        resp.sendRedirect(req.getContextPath() + "/carro/ver");
    }
}
