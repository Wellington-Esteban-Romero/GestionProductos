package org.gestion.productos.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.gestion.productos.models.Carro;
import org.gestion.productos.models.Pedido;
import org.gestion.productos.models.PedidoEstado;
import org.gestion.productos.models.Usuario;
import org.gestion.productos.services.LoginService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

@WebServlet("/pedidos/continuar")
public class PedidoContinuarServlet extends HttpServlet {

    @Inject
    private LoginService loginService;

    @Inject
    Carro carro;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<Usuario> usuarioOptional = loginService.getUsername(req);
        if (usuarioOptional.isPresent()) {

            Pedido pedido = new Pedido();
            pedido.setUsuario(usuarioOptional.get());
            PedidoEstado pedidoEstado = new PedidoEstado();
            pedidoEstado.setId(1L);
            pedido.setEstado(pedidoEstado);
            pedido.setFecha_pedido(LocalDate.now());
            pedido.setTotal(carro.getTotal());

            //pedidoService.guardar(pedido);
            req.setAttribute("pedido", pedido);
            getServletContext().getRequestDispatcher("/detalles_pedido.jsp").forward(req, resp);
        }
    }
}
