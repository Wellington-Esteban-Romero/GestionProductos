package org.gestion.productos.repositories;

import org.gestion.productos.models.Producto;
import org.gestion.productos.models.ReporteMensual;

import java.sql.SQLException;
import java.util.List;

public interface ProductoRepositoryJdbc extends PaginacionRepository<Producto> {
    int obtenerTotalProductos() throws SQLException;
    List<ReporteMensual> obtenerProductosAgregadosPorMes() throws SQLException;
    List<ReporteMensual> obtenerProductosVendidosPorMes() throws SQLException;
}
