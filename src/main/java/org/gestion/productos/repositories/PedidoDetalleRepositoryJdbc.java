package org.gestion.productos.repositories;

import org.gestion.productos.models.PedidoDetalle;

import java.sql.SQLException;
import java.util.List;

public interface PedidoDetalleRepositoryJdbc extends CrudRepository<PedidoDetalle> {
    List<PedidoDetalle> listar(Long pedido_id) throws SQLException;
}
