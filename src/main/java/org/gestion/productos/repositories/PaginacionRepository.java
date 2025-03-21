package org.gestion.productos.repositories;

import org.gestion.productos.dto.FiltroDTO;

import java.sql.SQLException;
import java.util.List;

public interface PaginacionRepository<T> {
    List<T> listar(int pagina, int tamanio_pagina) throws SQLException;
    List<T> listar(int pagina, int tamanio_pagina, Long usuario_id) throws SQLException;
    T porId(Long id) throws SQLException;
    void guardar(T obj) throws SQLException;
    void eliminar(Long id) throws SQLException;
    boolean existe(String nombre) throws SQLException;
    List<T> filtrar(FiltroDTO filtro) throws SQLException;
    int contar() throws SQLException;
}
