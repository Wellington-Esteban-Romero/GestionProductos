package org.gestion.productos.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.gestion.productos.models.Pedido;
import org.gestion.productos.services.PedidoService;
import org.gestion.productos.utils.Constantes;
import org.gestion.productos.utils.Utils;

import java.io.IOException;
import java.util.List;

@WebServlet({"/pedidos.html", "/pedidos"})
public class PedidoServlet extends HttpServlet {

    @Inject
    private PedidoService pedidoService;

    @Inject
    private Utils utils;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pagina = utils.parseInt(req.getParameter("pagina"));
        List<Pedido> pedidos = pedidoService.listarPedidos(pagina, Constantes.TAMANIO_PAGINA);
        int total = pedidoService.contarPedidos();
        int totalPaginas = (int) Math.ceil((double) total / Constantes.TAMANIO_PAGINA);

        req.setAttribute("title", req.getAttribute("title") + " - Listado de productos");
        req.setAttribute("pedidos", pedidos);
        req.setAttribute("pagina", pagina);
        req.setAttribute("totalPaginas", totalPaginas);

        getServletContext().getRequestDispatcher("/listar_pedidos.jsp").forward(req, resp);
    }
}
