package org.gestion.productos.repositories;

import org.gestion.productos.models.Usuario;

import java.sql.SQLException;

public interface UsuarioRepositoryJdbc extends CrudRepository<Usuario> {
    Usuario porUsername(String username) throws SQLException;
    boolean registrar(Usuario usuario) throws SQLException;
    boolean tieneRol(Long usuarioId, String rol) throws SQLException;
}
