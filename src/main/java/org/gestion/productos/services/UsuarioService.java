package org.gestion.productos.services;

import org.gestion.productos.models.Usuario;

import java.util.Optional;

public interface UsuarioService{
    Optional<Usuario> iniciarSesion(String username, String password);
}
