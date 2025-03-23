package org.gestion.productos.models;

public class ReporteMensual {
    private String mes;
    private int cantidad;

    public ReporteMensual(String mes, int cantidad) {
        this.mes = mes;
        this.cantidad = cantidad;
    }

    public String getMes() {
        return mes;
    }

    public int getCantidad() {
        return cantidad;
    }
}
