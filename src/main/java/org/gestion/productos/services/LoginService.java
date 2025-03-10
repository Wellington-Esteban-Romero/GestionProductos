package org.gestion.productos.services;

import jakarta.servlet.http.HttpServletRequest;
import org.gestion.productos.models.Usuario;

import java.util.Optional;

public interface LoginService {
    Optional<Usuario> getUsername(HttpServletRequest req);
}
