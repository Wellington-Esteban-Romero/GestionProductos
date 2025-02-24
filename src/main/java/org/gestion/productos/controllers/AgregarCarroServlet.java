package org.gestion.productos.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.gestion.productos.models.Carro;
import org.gestion.productos.models.ItemCarro;
import org.gestion.productos.models.Producto;
import org.gestion.productos.services.ProductoService;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/carro/agregar")
public class AgregarCarroServlet extends HttpServlet {

    @Inject
    private Carro carro;

    @Inject
    private ProductoService productoService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Optional<Producto> producto = productoService.porId(id);

        if (producto.isPresent()) {
            ItemCarro itemCarro = new ItemCarro(1, producto.get());
            carro.addItem(itemCarro);
        }
        resp.sendRedirect(req.getContextPath() + "/carro/ver");
    }
}
