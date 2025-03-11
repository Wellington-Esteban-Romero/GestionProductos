package org.gestion.productos.filters;


import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.gestion.productos.models.Usuario;
import org.gestion.productos.services.LoginService;
import org.gestion.productos.services.UsuarioService;

import java.io.IOException;
import java.util.Optional;

@WebFilter({"/productos/form/*", "/productos/eliminar/*", "/categorias", "/categorias/form/*", "/categorias/eliminar/*"})
public class RoleFilter implements Filter {

    @Inject
    private LoginService loginService;

    @Inject
    private UsuarioService usuarioService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        Optional<Usuario> usuario = loginService.getUsername((HttpServletRequest) servletRequest);

        if (usuario.isEmpty() || !usuarioService.esRol(usuario.get().getId(), "ROLE_ADMINISTRADOR")) {
            servletRequest.getRequestDispatcher("/errores/403.jsp").forward(servletRequest, servletResponse);
        }
        filterChain.doFilter(servletRequest, servletResponse);

    }
}
