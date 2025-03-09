package org.gestion.productos.models;

public class PedidoDetalle {
    private Long id;
    private Pedido pedido;
    private Producto producto;
    private int cantidad;
    private double total;

    public PedidoDetalle() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "PedidoDetalle{" +
                "id=" + id +
                ", pedido=" + pedido +
                ", producto=" + producto +
                ", cantidad=" + cantidad +
                ", total=" + total +
                '}';
    }
}
