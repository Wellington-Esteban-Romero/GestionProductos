package org.gestion.productos.repositories;

import jakarta.inject.Inject;
import org.gestion.productos.configs.MysqlConn;
import org.gestion.productos.configs.Repositorio;
import org.gestion.productos.models.Role;
import org.gestion.productos.models.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repositorio
public class UsuarioRepositoryJdbcImpl implements UsuarioRepositoryJdbc {

    @Inject
    @MysqlConn
    private Connection conn;

    @Override
    public Usuario porUsername(String username) throws SQLException {
        Usuario usuario = null;
        try (PreparedStatement ps = conn.prepareStatement("SELECT u.*, r.nombre as role FROM usuarios as u " +
                " INNER JOIN roles as r ON (u.rol_id = r.id) WHERE u.username = ?")
        ) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = getUsuario(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }

    @Override
    public boolean registrar(Usuario usuario) throws SQLException {
        boolean resultado = Boolean.FALSE;
        String sql = "INSERT INTO usuarios (nombre, apellidos, email, telefono, direccion, username, password, rol_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellidos());
            stmt.setString(3, usuario.getEmail());
            stmt.setInt(4, Integer.parseInt(usuario.getTelefono()));
            stmt.setString(5, usuario.getDireccion());
            stmt.setString(6, usuario.getUsername());
            stmt.setString(7, usuario.getPassword());
            stmt.setLong(8, usuario.getRole().getId());

            if (stmt.executeUpdate() > 0) {
                resultado = Boolean.TRUE;
            }
        }
        return resultado;
    }

    @Override
    public List<Usuario> listar() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement("SELECT u.*, r.nombre as role FROM usuarios as u " +
                " INNER JOIN roles as r ON (u.rol_id = r.id)")) {

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Usuario p = getUsuario(rs);
                    usuarios.add(p);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuarios;
    }

    @Override
    public Usuario porId(Long id) throws SQLException {
        return null;
    }

    @Override
    public void guardar(Usuario obj) throws SQLException {

    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }

    @Override
    public boolean existe(String name) throws SQLException {
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

    private static Usuario getUsuario(ResultSet rs) throws SQLException {
        Usuario usuario;
        usuario = new Usuario();
        usuario.setId(rs.getLong("id"));
        usuario.setNombre(rs.getString("nombre"));
        usuario.setApellidos(rs.getString("apellidos"));
        usuario.setEmail(rs.getString("email"));
        usuario.setTelefono(rs.getString("telefono"));
        usuario.setDireccion(rs.getString("direccion"));
        usuario.setUsername(rs.getString("username"));
        usuario.setPassword(rs.getString("password"));
        usuario.setActivo(rs.getBoolean("activo"));
        Role role = new Role();
        role.setId(rs.getLong("rol_id"));
        role.setNombre(rs.getString("role"));
        usuario.setRole(role);
        return usuario;
    }
}
