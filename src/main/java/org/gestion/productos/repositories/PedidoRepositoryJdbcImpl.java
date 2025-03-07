package org.gestion.productos.repositories;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;
import org.gestion.productos.configs.MysqlConn;
import org.gestion.productos.configs.Repositorio;
import org.gestion.productos.models.Pedido;

import java.sql.*;
import java.util.List;
import java.util.logging.Logger;

@Repositorio
public class PedidoRepositoryJdbcImpl implements PaginacionRepository<Pedido> {

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
        return List.of();
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
}
