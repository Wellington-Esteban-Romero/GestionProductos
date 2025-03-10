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
import org.gestion.productos.services.PedidoService;
import org.gestion.productos.services.UsuarioService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

@WebServlet("/pedidos/pendientes")
public class PedidoPendienteServlet extends HttpServlet {

    @Inject
    private LoginService loginService;

    @Inject
    private UsuarioService usuarioService;

    @Inject
    private PedidoService pedidoService;

    @Inject
    private Carro carro;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Optional<Usuario> usuarioOptional = loginService.getUsername(req);
        if (usuarioOptional.isPresent()) {
            //Usuario usuario = usuarioService.obtenerUsuarioPorUsername(username.get());

            Pedido pedido = new Pedido();
            pedido.setUsuario(usuarioOptional.get());
            PedidoEstado pedidoEstado = new PedidoEstado();
            pedidoEstado.setId(1L);
            pedido.setEstado(pedidoEstado);
            pedido.setFecha_pedido(LocalDate.now());
            pedido.setTotal(carro.getTotal());

            pedidoService.guardar(pedido);
        }
    }
}
