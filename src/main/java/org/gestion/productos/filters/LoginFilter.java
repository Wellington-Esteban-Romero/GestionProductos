package org.gestion.productos.filters;

import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.gestion.productos.models.Usuario;
import org.gestion.productos.services.LoginService;

import java.io.IOException;
import java.util.Optional;

@WebFilter({"/carro/*", "/productos/form/*", "/productos/eliminar/*",
        "/pedidos",
        "/categorias", "/categorias/form/*", "/categorias/eliminar/*"})
public class LoginFilter implements Filter {

    @Inject
    private LoginService loginService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
            IOException, ServletException {

        Optional<Usuario> usuario = loginService.getUsername((HttpServletRequest) servletRequest);
        if (usuario.isPresent()) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            servletRequest.getRequestDispatcher("/errores/401.jsp").forward(servletRequest, servletResponse);
        }
    }
}
