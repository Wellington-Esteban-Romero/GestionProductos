package org.gestion.productos.repositories;

import org.gestion.productos.models.Pedido;

import java.sql.SQLException;
import java.util.List;

public interface PedidoRepositoryJdbc extends PaginacionRepository<Pedido> {
    List<Pedido> listar(int pagina, int tamanio_pagina, Long usuario_id) throws SQLException;
}
