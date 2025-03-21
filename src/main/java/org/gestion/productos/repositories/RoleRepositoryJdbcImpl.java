package org.gestion.productos.repositories;

import jakarta.inject.Inject;
import org.gestion.productos.configs.MysqlConn;
import org.gestion.productos.configs.Repositorio;
import org.gestion.productos.models.Role;
import org.gestion.productos.utils.Constantes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repositorio
public class RoleRepositoryJdbcImpl implements CrudRepository<Role> {

    @Inject
    @MysqlConn
    private Connection conn;

    @Override
    public List<Role> listar() throws SQLException {
        List<Role> roles = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM roles")) {

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Role role = getRole(rs);
                    roles.add(role);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roles;
    }

    @Override
    public Role porId(Long id) throws SQLException {
        return null;
    }

    @Override
    public void guardar(Role obj) throws SQLException {

    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }

    @Override
    public boolean existe(String nombre) throws SQLException {
        return false;
    }

    @Override
    public boolean activar(Long id) throws SQLException {
        return false;
    }

    @Override
    public boolean desactivar(Long id) throws SQLException {
        return false;
    }

    private static Role getRole(ResultSet rs) throws SQLException {
        Role role = new Role();
        role.setId(rs.getLong(Constantes.CAMPO_ROLE_ID));
        role.setNombre(rs.getString(Constantes.CAMPO_ROLE_NOMBRE));
        role.setDescripcion(rs.getString(Constantes.CAMPO_ROLE_DESCRIPCION));
        return role;
    }
}
