package org.gestion.productos.services;

import org.gestion.productos.dto.ProductoFiltroDTO;
import org.gestion.productos.models.Categoria;
import org.gestion.productos.models.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> obtenerProductos(int pagina, int tamanio_pagina);
    Optional<Producto> porId(Long id);
    void guardar(Producto producto);
    void eliminar(Long id);
    List<Categoria> listarCategorias();
    int obtenerTotalProductos();
    List<Producto> obtenerUltimosProductos();
    List<Producto> buscarProductos(ProductoFiltroDTO filtro);
    int contarProductos();
    boolean existe(String nombre);
}
