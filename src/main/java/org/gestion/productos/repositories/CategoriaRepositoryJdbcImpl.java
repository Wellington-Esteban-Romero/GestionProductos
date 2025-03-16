package org.gestion.productos.repositories;

import jakarta.inject.Inject;
import org.gestion.productos.configs.MysqlConn;
import org.gestion.productos.configs.Repositorio;
import org.gestion.productos.models.Categoria;
import org.gestion.productos.utils.Constantes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repositorio
public class CategoriaRepositoryJdbcImpl implements CrudRepository<Categoria> {

    @Inject
    @MysqlConn
    private Connection conn;

    @Override
    public List<Categoria> listar() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        try (Statement ps = conn.createStatement();
        ResultSet rs = ps.executeQuery("SELECT * FROM categorias")) {
            while (rs.next()) {
                Categoria categoria = getCategoria(rs);
                categorias.add(categoria);
            }
        }
        return categorias;
    }

    @Override
    public Categoria porId(Long id) throws SQLException {
        Categoria categoria = null;
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM categorias WHERE id = ?")) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    categoria = getCategoria(rs);
                }
            }
        }
        return categoria;
    }

    @Override
    public void guardar(Categoria obj) throws SQLException {
        String sql;
        if (obj.getId() != null && (obj.getId() > 0)) {
            sql = "UPDATE categorias SET nombre = ?, descripcion = ? WHERE id = ?";
        } else {
            sql = "INSERT INTO categorias (nombre, descripcion) VALUES (?, ?)";
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, obj.getNombre());
            stmt.setString(2, obj.getDescripcion());
            if (obj.getId() != null && (obj.getId() > 0)) {
                stmt.setLong(3, obj.getId());
            }
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }

    @Override
    public boolean existe(String nombre) throws SQLException {
        boolean existe = false;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM categorias WHERE nombre = ?")) {
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                existe = rs.getInt(1) > 0;
            }
        }
        return existe;
    }

    @Override
    public boolean activar(Long id) throws SQLException {
        boolean response = Boolean.FALSE;

        try (PreparedStatement stmt = conn.prepareStatement("UPDATE categorias SET activo=1 WHERE id = ?")) {
            stmt.setLong(1, id);
            if (stmt.executeUpdate() > 0) {
                response = Boolean.TRUE;
            }
        }
        return response;
    }

    @Override
    public boolean desactivar(Long id) throws SQLException {
        boolean response = Boolean.FALSE;

        try (PreparedStatement stmt = conn.prepareStatement("UPDATE categorias SET activo=0 WHERE id = ?")) {
            stmt.setLong(1, id);
            if (stmt.executeUpdate() > 0) {
                response = Boolean.TRUE;
            }
        }
        return response;
    }

    private static Categoria getCategoria(ResultSet rs) throws SQLException {
        Categoria categoria = new Categoria();
        categoria.setId(rs.getLong(Constantes.CAMPO_CATEGORIA_ID));
        categoria.setNombre(rs.getString(Constantes.CAMPO_CATEGORIA_NOMBRE));
        categoria.setDescripcion(rs.getString(Constantes.CAMPO_CATEGORIA_DESCRIPCION));
        categoria.setActivo(rs.getBoolean(Constantes.CAMPO_CATEGORIA_ACTIVO));
        return categoria;
    }
}
