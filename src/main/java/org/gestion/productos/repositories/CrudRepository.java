package org.gestion.productos.repositories;

import java.sql.SQLException;
import java.util.List;

public interface CrudRepository<T> {
    List<T> listar() throws SQLException;
    T porId(Long id) throws SQLException;
    void guardar(T obj) throws SQLException;
    void eliminar(Long id) throws SQLException;
}
