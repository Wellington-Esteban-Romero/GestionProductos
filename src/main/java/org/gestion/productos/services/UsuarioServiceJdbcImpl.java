package org.gestion.productos.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.gestion.productos.exceptions.ServiceJdbcException;
import org.gestion.productos.models.Usuario;
import org.gestion.productos.repositories.UsuarioRepositoryJdbc;

import java.sql.SQLException;
import java.util.Optional;

@ApplicationScoped
public class UsuarioServiceJdbcImpl implements UsuarioService {

    @Inject
    private UsuarioRepositoryJdbc usuarioRepositoryJdbc;

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
