package org.gestion.productos.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.gestion.productos.models.Pedido;
import org.gestion.productos.models.PedidoDetalle;
import org.gestion.productos.models.Usuario;
import org.gestion.productos.services.LoginService;
import org.gestion.productos.services.PedidoService;
import org.gestion.productos.services.UsuarioService;
import org.gestion.productos.utils.Constantes;
import org.gestion.productos.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet({"/pedidos.html", "/pedidos"})
public class PedidoServlet extends HttpServlet {

    @Inject
    private PedidoService pedidoService;

    @Inject
    private LoginService loginService;

    @Inject
    private UsuarioService usuarioService;

    @Inject
    private Utils utils;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Optional<Usuario> usuarioOptional = loginService.getUsername(req);
        List<Pedido> pedidos;

        int pagina = utils.parseInt(req.getParameter("pagina"));

        if (usuarioService.esRol(usuarioOptional.get().getId(), "ROLE_ADMINISTRADOR")){
            pedidos = pedidoService.listarPedidos(pagina, Constantes.TAMANIO_PAGINA);
        } else {
            pedidos = pedidoService.listarPedidos(pagina, Constantes.TAMANIO_PAGINA, usuarioOptional.get().getId());
        }

        int total = pedidoService.contarPedidos();
        int totalPaginas = (int) Math.ceil((double) total / Constantes.TAMANIO_PAGINA);

        List<PedidoDetalle> pedidosDetalle = new ArrayList<>();

        pedidos.forEach(pedido -> {
            List<PedidoDetalle> detalles = pedidoService.listarPedidosDetalles(pedido.getId(), usuarioOptional.get().getId());
            pedidosDetalle.addAll(detalles);
        });

        System.out.println("****** DETALLES: " + pedidosDetalle);

        req.setAttribute("title", req.getAttribute("title") + " - Listado de productos");
        req.setAttribute("pedidos", pedidos);
        req.setAttribute("detalles", pedidosDetalle);
        req.setAttribute("pagina", pagina);
        req.setAttribute("totalPaginas", totalPaginas);

        getServletContext().getRequestDispatcher("/listar_pedidos.jsp").forward(req, resp);
    }
}
