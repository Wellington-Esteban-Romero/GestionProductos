package org.gestion.productos.repositories;

import org.gestion.productos.models.Usuario;

import java.sql.SQLException;

public interface UsuarioRepositoryJdbc extends CrudRepository<Usuario> {
    Usuario porUsername(String email) throws SQLException;
}
