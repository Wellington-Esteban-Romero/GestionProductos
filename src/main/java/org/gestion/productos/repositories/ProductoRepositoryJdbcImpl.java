package org.gestion.productos.repositories;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;
import org.gestion.productos.configs.MysqlConn;
import org.gestion.productos.configs.Repositorio;
import org.gestion.productos.dto.ProductoFiltroDTO;
import org.gestion.productos.models.Categoria;
import org.gestion.productos.models.Producto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repositorio
public class ProductoRepositoryJdbcImpl implements PaginacionRepository<Producto> {

    @Inject
    @MysqlConn
    private Connection conn;

    @Inject
    private Logger log;

    @PostConstruct
    public void inicializar() {
        log.info("Inicializando " + this.getClass().getName());
    }

    @PreDestroy
    public void destruir() {
        log.info("Destruyendo " + this.getClass().getName());
    }

    @Override
    public List<Producto> listar(int pagina, int tamanio_pagina) throws SQLException {
        List<Producto> productos = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement("SELECT p.*, c.nombre as categoria FROM productos as p " +
                " INNER JOIN categorias as c ON (p.categoria_id = c.id) ORDER BY id LIMIT ? OFFSET ?")) {
            stmt.setInt(1, tamanio_pagina);
            stmt.setInt(2, pagina * tamanio_pagina);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Producto p = getProducto(rs);
                    productos.add(p);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productos;
    }

    @Override
    public List<Producto> listar(int pagina, int tamanio_pagina, Long usuario_id) throws SQLException {
        return List.of();
    }

    @Override
    public Producto porId(Long id) throws SQLException {
        Producto producto = new Producto();
        try (PreparedStatement stmt = conn.prepareStatement("SELECT p.*, c.nombre as categoria FROM productos as p " +
                " INNER JOIN categorias as c ON (p.categoria_id = c.id) WHERE p.id = ?")
        ) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    producto = getProducto(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }

    @Override
    public void guardar(Producto obj) throws SQLException {
        String sql;
        if (obj.getId() != null && (obj.getId() > 0)) {
            sql = "UPDATE productos SET nombre = ?, codigo = ?, descripcion = ?, precio = ?, stock = ?, categoria_id = ?, fecha_registro = ?, fecha_modificacion = ?, imagen = ? WHERE id = ?";
        } else {
            sql = "INSERT INTO productos (nombre, codigo, descripcion, precio, stock, categoria_id, fecha_registro, imagen) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, obj.getNombre());
            stmt.setString(2, obj.getCodigo());
            stmt.setString(3, obj.getDescripcion());
            stmt.setDouble(4, obj.getPrecio());
            stmt.setInt(5, obj.getStock());
            stmt.setLong(6, obj.getCategoria().getId());
            if (obj.getId() != null && (obj.getId() > 0)) {
                stmt.setDate(7, Date.valueOf(obj.getFechaRegistro()));
                stmt.setDate(8, Date.valueOf(LocalDate.now()));
                stmt.setString(9, obj.getImagen());
                stmt.setLong(10, obj.getId());
            } else {
                stmt.setDate(7, Date.valueOf(obj.getFechaRegistro()));
                stmt.setString(8, obj.getImagen());
            }
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "DELETE FROM productos WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public boolean existe(String nombre) throws SQLException {
        boolean existe = false;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM productos WHERE nombre = ?")) {
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                existe = rs.getInt(1) > 0;
            }
        }
        return existe;
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

    @Override
    public List<Producto> filtar(ProductoFiltroDTO filtro) throws SQLException {
        List<Producto> lista = new ArrayList<>();
        List<Object> params = new ArrayList<>();

        String sql = "SELECT p.*, c.nombre as categoria FROM productos as p INNER JOIN categorias as c ON (p.categoria_id = c.id) " +
                "WHERE 1=1";

        if (filtro.getNombre() != null && !filtro.getNombre().isEmpty()) {
            sql += " AND p.nombre LIKE ?";
            params.add("%" + filtro.getNombre() + "%");
        }
        if (filtro.getTipo() != null && !filtro.getTipo().isEmpty()) {
            sql += " AND c.nombre LIKE ?";
            params.add("%" + filtro.getTipo() + "%");
        }
        if (filtro.getPrecioMin() != null) {
            sql += " AND p.precio >= ?";
            params.add(filtro.getPrecioMin());
        }
        if (filtro.getPrecioMax() != null) {
            sql += " AND p.precio <= ?";
            params.add(filtro.getPrecioMax());
        }

        if (filtro.getFechaInicio() != null) {
            sql += " AND p.fecha_registro >= ?";
            params.add(filtro.getFechaInicio());
        }

        if (filtro.getFechaFin() != null) {
            sql += " AND p.fecha_registro <= ?";
            params.add(filtro.getFechaFin());
        }

        log.info("******** " + sql + " *********");
        log.info("******** " + params + " *********");

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.size(); i++) {
                Object param = params.get(i);
                if (param instanceof String) {
                    stmt.setString(i + 1, (String) param);
                } else if (param instanceof Long) {
                    stmt.setLong(i + 1, (Long) param);
                } else if (param instanceof LocalDate) {
                    stmt.setDate(i + 1, Date.valueOf((LocalDate) param));
                }
            }
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(getProducto(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public int contar() throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM productos");
             ResultSet rs = stmt.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    private static Producto getProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getLong("id"));
        p.setNombre(rs.getString("nombre"));
        p.setCodigo(rs.getString("codigo"));
        p.setDescripcion(rs.getString("descripcion"));
        p.setPrecio(rs.getDouble("precio"));
        p.setStock(rs.getInt("stock"));
        p.setFechaRegistro(rs.getDate("fecha_registro").toLocalDate());
        p.setFechaModificacion((rs.getDate("fecha_modificacion") != null)?
                rs.getDate("fecha_modificacion").toLocalDate() : LocalDate.of(1980,1,1));
        p.setImagen(rs.getString("imagen"));
        Categoria c = new Categoria();
        c.setId(rs.getLong("categoria_id"));
        c.setNombre(rs.getString("categoria"));
        p.setCategoria(c);
        return p;
    }
}
