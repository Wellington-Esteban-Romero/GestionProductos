package org.gestion.productos.models;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;
import org.gestion.productos.configs.Carrito;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

@Carrito
public class Carro implements Serializable {

    private List<ItemCarro> items;

    @Inject
    private transient Logger log;

    @PostConstruct
    public void inicializar () {
        items = new ArrayList<>();
        log.info("Carro inicializado");
    }

    @PreDestroy
    public void destruir () {
        log.info("Carro destruyendo");
    }

    public List<ItemCarro> getItems() {
        return items;
    }

    public void addItem(ItemCarro item) {
        if (!items.contains(item)) {
            items.add(item);
        } else {
            Optional<ItemCarro> optionalItemCarro = items.stream()
                    .filter(itemCarro -> itemCarro.equals(item))
                    .findAny();
            if (optionalItemCarro.isPresent()) {
                ItemCarro itemCarro = optionalItemCarro.get();
                itemCarro.setCantidad(itemCarro.getCantidad() + 1);
            }
        }
    }

    public double getTotal() {
        return items.stream()
                .mapToDouble(ItemCarro::getImporte)
                .sum();
    }

    public void actualizarItem(ItemCarro item, Integer cantidad) {
        Optional<ItemCarro> optionalItemCarro = items.stream()
                .filter(itemCarro -> itemCarro.equals(item))
                .findAny();
        if (optionalItemCarro.isPresent()) {
            ItemCarro itemCarro = optionalItemCarro.get();
            itemCarro.setCantidad(cantidad);
        }
    }

    public void eliminarItem(Long id) {
        items.removeIf(itemCarro -> Objects.equals(itemCarro.getProducto().getId(), id));
    }
}
