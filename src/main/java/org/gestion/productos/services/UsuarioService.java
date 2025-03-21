package org.gestion.productos.services;

import org.gestion.productos.models.Usuario;

import java.util.Optional;

public interface UsuarioService {
    Optional<Usuario> iniciarSesion(String username, String password);
    boolean registrar(Usuario usuario);
    boolean esRol(Long id, String rol);
    boolean existeUsuario(String nombre);
}
