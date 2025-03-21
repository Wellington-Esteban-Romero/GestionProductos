package org.gestion.productos.dto;

import java.time.LocalDate;

public class PedidoFiltroDTO implements FiltroDTO {
    private String nombre;
    private String tipo;
    private Long precioMin;
    private Long precioMax;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public PedidoFiltroDTO(String nombre, String tipo, Long precioMin, Long precioMax, LocalDate fechaInicio, LocalDate fechaFin) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.precioMin = precioMin;
        this.precioMax = precioMax;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getPrecioMin() {
        return precioMin;
    }

    public void setPrecioMin(Long precioMin) {
        this.precioMin = precioMin;
    }

    public Long getPrecioMax() {
        return precioMax;
    }

    public void setPrecioMax(Long precioMax) {
        this.precioMax = precioMax;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public String toString() {
        return "ProductoFiltroDTO{" +
                "nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", precioMin=" + precioMin +
                ", precioMax=" + precioMax +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                '}';
    }
}
