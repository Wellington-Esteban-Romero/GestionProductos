package org.gestion.productos.services;



import org.gestion.productos.exceptions.ServiceJdbcException;
import org.gestion.productos.models.Producto;
import org.gestion.productos.repositories.ProductoRepositoryJdbcImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductoServiceJdbcImpl implements ProductoService {
    private final ProductoRepositoryJdbcImpl repositoryJdbc;

    public ProductoServiceJdbcImpl(Connection conn) {
        this.repositoryJdbc = new ProductoRepositoryJdbcImpl(conn);
    }

    @Override
    public List<Producto> getProductos() {
        try {
            return repositoryJdbc.listar();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Producto> proId(Long id) {
        try {
            return Optional.ofNullable(repositoryJdbc.porId(id));
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public int obtenerTotalProductos() {
        try {
            return repositoryJdbc.obtenerTotalProductos();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Producto> obtenerUltimosProductos() {
        try {
            return repositoryJdbc.obtenerUltimosProductos();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }
}
