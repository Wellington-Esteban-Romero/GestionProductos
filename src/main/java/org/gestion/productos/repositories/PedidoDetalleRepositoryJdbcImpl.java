package org.gestion.productos.repositories;

import jakarta.inject.Inject;
import org.gestion.productos.configs.MysqlConn;
import org.gestion.productos.configs.Repositorio;
import org.gestion.productos.models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repositorio
public class PedidoDetalleRepositoryJdbcImpl implements PedidoDetalleRepositoryJdbc {

    @Inject
    @MysqlConn
    private Connection conn;

    @Override
    public List<PedidoDetalle> listar(Long pedido_id) throws SQLException {
        List<PedidoDetalle> pedidoDetalles = new ArrayList<>();
        System.out.println("*********IDS pedidos repo IMPL: "+pedido_id);
        try (PreparedStatement stmt = conn.prepareStatement("SELECT dp.*, pr.nombre as nombre_producto, pr.precio as precio_producto " +
                " FROM detallepedidos as dp " +
                " INNER JOIN pedidos as p ON (dp.pedido_id = p.id) " +
                " INNER JOIN productos as pr ON (dp.producto_id = pr.id) " +
                " WHERE dp.pedido_id = ?")) {

            stmt.setLong(1, pedido_id);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    PedidoDetalle pedidoDetalle = getPedidoDetalle(rs);
                    pedidoDetalles.add(pedidoDetalle);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pedidoDetalles;
    }

    @Override
    public List<PedidoDetalle> listar() throws SQLException {
        return List.of();
    }

    @Override
    public PedidoDetalle porId(Long id) throws SQLException {
        return null;
    }

    @Override
    public void guardar(PedidoDetalle obj) throws SQLException {

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

    private static PedidoDetalle getPedidoDetalle(ResultSet rs) throws SQLException {
        PedidoDetalle pedidoDetalle = new PedidoDetalle();
        pedidoDetalle.setId(rs.getLong("id"));
        pedidoDetalle.setCantidad(rs.getInt("cantidad"));
        pedidoDetalle.setTotal(rs.getDouble("total"));
        Pedido pe = new Pedido();
        pe.setId(rs.getLong("pedido_id"));
        pedidoDetalle.setPedido(pe);
        Producto p = new Producto();
        p.setId(rs.getLong("producto_id"));
        p.setNombre(rs.getString("nombre_producto"));
        p.setPrecio(rs.getDouble("precio_producto"));
        pedidoDetalle.setProducto(p);
        return pedidoDetalle;
    }

}
