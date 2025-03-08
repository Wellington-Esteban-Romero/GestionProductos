package org.gestion.productos.services;

import org.gestion.productos.models.Pedido;

import java.util.List;

public interface PedidoService {
    List<Pedido> listarPedidos(int pagina, int tamanio_pagina);
    void guardar(Pedido obj);
    int contarPedidos();
}
