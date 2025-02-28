package org.gestion.productos.services;

import jakarta.inject.Inject;
import org.gestion.productos.configs.Services;
import org.gestion.productos.dto.ProductoFiltroDTO;
import org.gestion.productos.exceptions.ServiceJdbcException;
import org.gestion.productos.models.Categoria;
import org.gestion.productos.models.Producto;
import org.gestion.productos.repositories.CrudRepository;
import org.gestion.productos.repositories.PaginacionRepository;
import org.gestion.productos.repositories.ProductoRepositoryJdbc;
import org.gestion.productos.repositories.ProductoRepositoryJdbcImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Services
public class ProductoServiceJdbcImpl implements ProductoService {

    @Inject
    private PaginacionRepository<Producto> repositoryJdbcProducto;

    @Inject
    private CrudRepository<Categoria> repositoryJdbcCategoria;

    @Inject
    private ProductoRepositoryJdbc productoRepositoryJdbc;

    @Override
    public List<Producto> obtenerProductos(int pagina, int tamanio_pagina) {
        try {
            return repositoryJdbcProducto.listar(pagina, tamanio_pagina);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Producto> porId(Long id) {
        try {
            return Optional.ofNullable(repositoryJdbcProducto.porId(id));
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void guardar(Producto producto) {
        try {
            repositoryJdbcProducto.guardar(producto);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            repositoryJdbcProducto.eliminar(id);
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
    public Optional<Categoria> porIdCategoria(Long id) {
        try {
            return Optional.ofNullable(repositoryJdbcCategoria.porId(id));
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public int obtenerTotalProductos() {
        try {
            return ((ProductoRepositoryJdbcImpl)repositoryJdbcProducto).obtenerTotalProductos();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Producto> obtenerUltimosProductos() {
        try {
            return ((ProductoRepositoryJdbcImpl)repositoryJdbcProducto).obtenerUltimosProductos();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Producto> buscarProductos(ProductoFiltroDTO filtro) {
        try {
            return productoRepositoryJdbc.buscarProductos(filtro);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public int contarProductos() {
        try {
            return productoRepositoryJdbc.contarProductos();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public boolean existe(String nombre) {
        try {
            return repositoryJdbcProducto.existe(nombre);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }
}
