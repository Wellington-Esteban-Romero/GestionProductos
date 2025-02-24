package org.gestion.productos.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.gestion.productos.configs.Repositorio;

import java.util.Optional;

@Repositorio
public class LoginServiceSessionImpl implements LoginService {

    @Override
    public Optional<String> getUsername(HttpServletRequest req) {
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");
        if (username != null) {
            return Optional.of(username);
        }
        return Optional.empty();
    }
}
