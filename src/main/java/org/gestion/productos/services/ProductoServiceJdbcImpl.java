package org.gestion.productos.services;



import org.gestion.productos.exceptions.ServiceJdbcException;
import org.gestion.productos.models.Categoria;
import org.gestion.productos.models.Producto;
import org.gestion.productos.repositories.CategoriaRepositoryJdbcImpl;
import org.gestion.productos.repositories.CrudRepository;
import org.gestion.productos.repositories.ProductoRepositoryJdbcImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductoServiceJdbcImpl implements ProductoService {
    private final CrudRepository<Producto> repositoryJdbcProducto;
    private final CrudRepository<Categoria> repositoryJdbcCategoria;

    public ProductoServiceJdbcImpl(Connection conn) {
        this.repositoryJdbcProducto = new ProductoRepositoryJdbcImpl(conn);
        this.repositoryJdbcCategoria = new CategoriaRepositoryJdbcImpl(conn);
    }

    @Override
    public List<Producto> getProductos() {
        try {
            return repositoryJdbcProducto.listar();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Producto> proId(Long id) {
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
}
