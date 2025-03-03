package org.gestion.productos.services;

import jakarta.inject.Inject;
import org.gestion.productos.configs.Services;
import org.gestion.productos.exceptions.ServiceJdbcException;
import org.gestion.productos.models.Categoria;
import org.gestion.productos.repositories.CrudRepository;

import java.sql.SQLException;
import java.util.Optional;

@Services
public class CategoriaSeviceJdbcImpl implements CategoriaService{
    @Inject
    private CrudRepository<Categoria> repositoryJdbcCategoria;

    @Override
    public void guardar(Categoria categoria) {
        try {
            repositoryJdbcCategoria.guardar(categoria);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public boolean existe(String nombre) {
        try {
            return repositoryJdbcCategoria.existe(nombre);
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
    public boolean activar(Long id) {
        try {
            return repositoryJdbcCategoria.activar(id);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public boolean desactivar(Long id) {
        try {
            return repositoryJdbcCategoria.desactivar(id);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }
}
