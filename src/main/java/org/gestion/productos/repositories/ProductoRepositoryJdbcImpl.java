package org.gestion.productos.repositories;

import org.gestion.productos.exceptions.ServiceJdbcException;
import org.gestion.productos.models.Categoria;
import org.gestion.productos.models.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositoryJdbcImpl implements CrudRepository<Producto> {

    private final Connection conn;

    public ProductoRepositoryJdbcImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> productos = new ArrayList<>();

        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT p.*, c.nombre as categoria FROM productos as p " +
                    " INNER JOIN categorias as c ON (p.categoria_id = c.id)");

            while (rs.next()) {
                Producto p = getProducto(rs);

                productos.add(p);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productos;
    }

    @Override
    public Producto porId(Long id) throws SQLException {
        return null;
    }

    @Override
    public void guardar(Producto obj) throws SQLException {

    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }

    public int obtenerTotalProductos() throws SQLException {
        int total = 0;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) AS total FROM productos");
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public List<Producto> obtenerUltimosProductos() throws SQLException {
        List<Producto> lista = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement("SELECT p.*, c.nombre as categoria FROM productos as p " +
                " INNER JOIN categorias as c ON (p.categoria_id = c.id) ORDER BY id DESC LIMIT 5");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(getProducto(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private static Producto getProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getLong("id"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecio(rs.getInt("precio"));
        p.setFechaRegistro(rs.getDate("fecha_registro").toLocalDate());
        Categoria c = new Categoria();
        c.setId(rs.getLong("categoria_id"));
        c.setNombre(rs.getString("categoria"));
        p.setCategoria(c);
        return p;
    }
}
