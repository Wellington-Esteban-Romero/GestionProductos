package org.gestion.productos.repositories;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;
import org.gestion.productos.configs.MysqlConn;
import org.gestion.productos.configs.Repositorio;
import org.gestion.productos.dto.FiltroDTO;
import org.gestion.productos.dto.PedidoFiltroDTO;
import org.gestion.productos.dto.ProductoFiltroDTO;
import org.gestion.productos.models.Pedido;
import org.gestion.productos.models.PedidoEstado;
import org.gestion.productos.models.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repositorio
public class PedidoRepositoryJdbcImpl implements PaginacionRepository<Pedido>{

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
    public List<Pedido> listar(int pagina, int tamanio_pagina) throws SQLException {
        List<Pedido> pedidos = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement("SELECT p.*, u.nombre as usuario_nombre, u.apellidos as usuario_ape, " +
                " pe.nombre as estado " +
                " FROM pedidos as p " +
                " INNER JOIN usuarios as u ON (p.usuario_id = u.id) " +
                " INNER JOIN pedidoestados as pe ON (p.estado_id = pe.id) ORDER BY id LIMIT ? OFFSET ?")) {
            stmt.setInt(1, tamanio_pagina);
            stmt.setInt(2, pagina * tamanio_pagina);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Pedido p = getPedido(rs);
                    pedidos.add(p);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pedidos;
    }

    @Override
    public List<Pedido> listar(int pagina, int tamanio_pagina, Long usuario_id) throws SQLException {
        List<Pedido> pedidos = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement("SELECT p.*, u.nombre as usuario_nombre, u.apellidos as usuario_ape, " +
                " pe.nombre as estado " +
                " FROM pedidos as p " +
                " INNER JOIN usuarios as u ON (p.usuario_id = u.id) " +
                " INNER JOIN pedidoestados as pe ON (p.estado_id = pe.id) WHERE u.id = ? ORDER BY id LIMIT ? OFFSET ?")) {
            stmt.setLong(1, usuario_id);
            stmt.setInt(2, tamanio_pagina);
            stmt.setInt(3, pagina * tamanio_pagina);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Pedido p = getPedido(rs);
                    pedidos.add(p);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pedidos;
    }

    private static Pedido getPedido(ResultSet rs) throws SQLException {
        Pedido p = new Pedido();
        p.setId(rs.getLong("id"));
        Usuario u = new Usuario();
        u.setId(rs.getLong("usuario_id"));
        u.setNombre(rs.getString("usuario_nombre"));
        u.setApellidos(rs.getString("usuario_ape"));
        p.setUsuario(u);
        PedidoEstado pe = new PedidoEstado();
        pe.setId(rs.getLong("estado_id"));
        pe.setNombre(rs.getString("estado"));
        p.setEstado(pe);
        p.setFecha_pedido(rs.getDate("fecha_pedido").toLocalDate());
        p.setTotal(rs.getDouble("total"));
        return p;
    }

    @Override
    public Pedido porId(Long id) throws SQLException {
        return null;
    }

    @Override
    public void guardar(Pedido obj) throws SQLException {

        String sql = "INSERT INTO pedidos (usuario_id, estado_id, fecha_pedido, total) " +
                "VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmtPedido = conn.prepareStatement(sql)) {
            stmtPedido.setLong(1, obj.getUsuario().getId());
            stmtPedido.setLong(2, obj.getEstado().getId());
            stmtPedido.setDate(3, Date.valueOf(obj.getFecha_pedido()));
            stmtPedido.setDouble(4, obj.getTotal());
            stmtPedido.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }

    @Override
    public boolean existe(String nombre) throws SQLException {
        return false;
    }

    @Override
    public List<Pedido> filtrar(FiltroDTO filtrodto) throws SQLException {
        return List.of();
    }

    @Override
    public int contar() throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM pedidos");
             ResultSet rs = stmt.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }
}
