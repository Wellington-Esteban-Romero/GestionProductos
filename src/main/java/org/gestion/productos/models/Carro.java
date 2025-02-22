package org.gestion.productos.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Carro {
    private final List<ItemCarro> items;

    public Carro() {
        items = new ArrayList<>();
    }

    public List<ItemCarro> getItems() {
        return items;
    }

    public void addItem(ItemCarro item) {
        if (!items.contains(item)) {
            items.add(item);
        } else {
            Optional<ItemCarro>  optionalItemCarro = items.stream()
                    .filter(itemCarro -> itemCarro.equals(item))
                    .findAny();
            if (optionalItemCarro.isPresent()) {
                ItemCarro itemCarro = optionalItemCarro.get();
                itemCarro.setCantidad(itemCarro.getCantidad() + 1);
            }
        }
    }

    public int getTotal() {
        return items.stream()
                .mapToInt(ItemCarro::getImporte)
                .sum();
    }

    public void actualizarItem (ItemCarro item, Integer cantidad) {
        Optional<ItemCarro>  optionalItemCarro = items.stream()
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
