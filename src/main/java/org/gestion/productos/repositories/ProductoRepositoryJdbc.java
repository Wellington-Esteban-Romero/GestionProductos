package org.gestion.productos.repositories;

import org.gestion.productos.models.Producto;

import java.sql.SQLException;
import java.util.List;

public interface ProductoRepositoryJdbc extends CrudRepository<Producto> {
    List<Producto> buscarProductos(String nombre, Long precioMin, Long precioMax) throws SQLException;
}
