package org.gestion.productos.repositories;

import java.sql.SQLException;
import java.util.List;

public interface PaginacionRepository<T> {
    List<T> listar(int pagina, int tamanio_pagina) throws SQLException;
    T porId(Long id) throws SQLException;
    void guardar(T obj) throws SQLException;
    void eliminar(Long id) throws SQLException;
}
