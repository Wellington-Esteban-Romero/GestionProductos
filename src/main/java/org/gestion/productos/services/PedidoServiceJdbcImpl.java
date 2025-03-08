package org.gestion.productos.services;

import jakarta.inject.Inject;
import org.gestion.productos.configs.Services;
import org.gestion.productos.exceptions.ServiceJdbcException;
import org.gestion.productos.models.Pedido;
import org.gestion.productos.repositories.PaginacionRepository;

import java.sql.SQLException;
import java.util.List;

@Services
public class PedidoServiceJdbcImpl implements PedidoService {

    @Inject
    private PaginacionRepository<Pedido> repositoryJdbc;

    @Override
    public List<Pedido> listarPedidos(int pagina, int tamanio_pagina) {
        try {
            return repositoryJdbc.listar(pagina, tamanio_pagina);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void guardar(Pedido obj) {
        try {
            repositoryJdbc.guardar(obj);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public int contarPedidos() {
        try {
            return repositoryJdbc.contar();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }
}
