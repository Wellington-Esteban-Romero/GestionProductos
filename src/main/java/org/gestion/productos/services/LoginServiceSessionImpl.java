package org.gestion.productos.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.gestion.productos.configs.Services;
import org.gestion.productos.models.Usuario;

import java.util.Optional;

@Services
public class LoginServiceSessionImpl implements LoginService {

    @Override
    public Optional<Usuario> getUsername(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null) {
            return Optional.of(usuario);
        }
        return Optional.empty();
    }
}
