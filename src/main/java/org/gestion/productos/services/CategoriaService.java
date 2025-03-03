package org.gestion.productos.services;

import org.gestion.productos.models.Categoria;

import java.util.Optional;

public interface CategoriaService {
    void guardar(Categoria categoria);
    boolean existe(String nombre);
    Optional<Categoria> porIdCategoria(Long id);
    boolean activar(Long id);
    boolean desactivar(Long id);
}

