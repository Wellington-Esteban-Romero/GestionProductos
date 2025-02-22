package org.gestion.productos.repositories;

import org.gestion.productos.models.Producto;

import java.sql.SQLException;
import java.util.Optional;

public interface ProductoRepositoryJdbc extends CrudRepository<Producto> {
    Optional<Producto> buscarProducto(String nombre) throws SQLException;
}
