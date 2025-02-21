package org.gestion.productos.services;

import org.gestion.productos.models.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> getProductos();
    Optional<Producto> proId(Long id);
    int obtenerTotalProductos();
    List<Producto> obtenerUltimosProductos();
}
