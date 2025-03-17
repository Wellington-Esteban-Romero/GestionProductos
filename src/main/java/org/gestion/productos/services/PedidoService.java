package org.gestion.productos.services;

import org.gestion.productos.models.Pedido;
import org.gestion.productos.models.PedidoDetalle;

import java.util.List;

public interface PedidoService {
    List<Pedido> listarPedidos(int pagina, int tamanio_pagina);
    List<Pedido> listarPedidos(int pagina, int tamanio_pagina, Long usuario_id);
    List<PedidoDetalle> listarPedidosDetalles(Long pedido_id, Long usuario_id);
    void guardar(Pedido obj);
    int contarPedidos();
}
