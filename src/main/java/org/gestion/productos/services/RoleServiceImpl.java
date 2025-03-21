package org.gestion.productos.services;

import jakarta.inject.Inject;
import org.gestion.productos.configs.Services;
import org.gestion.productos.exceptions.ServiceJdbcException;
import org.gestion.productos.models.Role;
import org.gestion.productos.repositories.CrudRepository;

import java.sql.SQLException;
import java.util.List;

@Services
public class RoleServiceImpl implements RoleService {

    @Inject
    private CrudRepository<Role> repository;

    @Override
    public List<Role> listarRoles() {
        try {
            return repository.listar();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }
}
