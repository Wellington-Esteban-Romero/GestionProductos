package org.gestion.productos.models;


import java.time.LocalDate;

public class Producto {
    private Long id;
    private String nombre;
    private String descripcion;
    private Categoria categoria;
    private int precio;
    private int stock;
    private LocalDate fechaRegistro;

    public Producto() {
    }

    public Producto(Long id, String nombre, String descripcion, String tipo, int precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        Categoria categoria = new Categoria();
        categoria.setNombre(tipo);
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}

