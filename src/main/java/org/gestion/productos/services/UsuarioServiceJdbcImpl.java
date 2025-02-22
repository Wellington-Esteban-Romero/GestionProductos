package org.gestion.productos.services;

import org.gestion.productos.exceptions.ServiceJdbcException;
import org.gestion.productos.models.Usuario;
import org.gestion.productos.repositories.UsuarioRepositoryJdbc;
import org.gestion.productos.repositories.UsuarioRepositoryJdbcImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class UsuarioServiceJdbcImpl implements UsuarioService {
    private final UsuarioRepositoryJdbc usuarioRepositoryJdbc;

    public UsuarioServiceJdbcImpl(Connection conn) {
        usuarioRepositoryJdbc = new UsuarioRepositoryJdbcImpl(conn);
    }

    @Override
    public Optional<Usuario> iniciarSesion(String username, String password) {
        try {
            return Optional.ofNullable(usuarioRepositoryJdbc.porUsername(username))
                    .filter(u -> u.getPassword().equals(password));
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }
}
