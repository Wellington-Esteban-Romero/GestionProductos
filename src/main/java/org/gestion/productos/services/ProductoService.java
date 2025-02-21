package org.gestion.productos.services;

import org.gestion.productos.models.Categoria;
import org.gestion.productos.models.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> getProductos();
    Optional<Producto> proId(Long id);
    void guardar(Producto producto);
    void eliminar(Long id);
    List<Categoria> listarCategorias();
    Optional<Categoria> porIdCategoria(Long id);
    int obtenerTotalProductos();
    List<Producto> obtenerUltimosProductos();
}
