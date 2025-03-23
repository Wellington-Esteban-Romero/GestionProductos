package org.gestion.productos.services;

import jakarta.inject.Inject;
import org.gestion.productos.configs.Services;
import org.gestion.productos.dto.ProductoFiltroDTO;
import org.gestion.productos.exceptions.ServiceJdbcException;
import org.gestion.productos.models.Categoria;
import org.gestion.productos.models.Producto;
import org.gestion.productos.models.ReporteMensual;
import org.gestion.productos.repositories.CrudRepository;
import org.gestion.productos.repositories.ProductoRepositoryJdbc;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Services
public class ProductoServiceJdbcImpl implements ProductoService {

    @Inject
    private ProductoRepositoryJdbc productoRepositoryJdbcImpl;

    @Inject
    private CrudRepository<Categoria> repositoryJdbcCategoria;


    @Override
    public List<Producto> obtenerProductos(int pagina, int tamanio_pagina) {
        try {
            return productoRepositoryJdbcImpl.listar(pagina, tamanio_pagina);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Producto> porId(Long id) {
        try {
            return Optional.ofNullable(productoRepositoryJdbcImpl.porId(id));
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void guardar(Producto producto) {
        try {
            productoRepositoryJdbcImpl.guardar(producto);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            productoRepositoryJdbcImpl.eliminar(id);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Categoria> listarCategorias() {
        try {
            return repositoryJdbcCategoria.listar();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public int obtenerTotalProductos() {
        try {
            return productoRepositoryJdbcImpl.obtenerTotalProductos();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<ReporteMensual> obtenerProductosAgregadosPorMes() {
        try {
            return productoRepositoryJdbcImpl.obtenerProductosAgregadosPorMes();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Producto> buscarProductos(ProductoFiltroDTO filtro) {
        try {
            return productoRepositoryJdbcImpl.filtrar(filtro);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public int contarProductos() {
        try {
            return productoRepositoryJdbcImpl.contar();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public boolean existe(String nombre) {
        try {
            return productoRepositoryJdbcImpl.existe(nombre);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }
}
