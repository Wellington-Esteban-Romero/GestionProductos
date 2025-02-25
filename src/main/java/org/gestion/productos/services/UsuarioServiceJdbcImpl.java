package org.gestion.productos.services;

import jakarta.inject.Inject;
import org.gestion.productos.configs.Services;
import org.gestion.productos.exceptions.ServiceJdbcException;
import org.gestion.productos.models.Usuario;
import org.gestion.productos.repositories.UsuarioRepositoryJdbc;
import org.gestion.productos.utils.PasswordUtil;

import java.sql.SQLException;
import java.util.Optional;

@Services
public class UsuarioServiceJdbcImpl implements UsuarioService {

    @Inject
    private UsuarioRepositoryJdbc usuarioRepositoryJdbc;

    @Override
    public Optional<Usuario> iniciarSesion(String username, String password) {
        try {
            return Optional.ofNullable(usuarioRepositoryJdbc.porUsername(username))
                    .filter(u -> PasswordUtil.checkPassword(password, u.getPassword()));
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }
}
