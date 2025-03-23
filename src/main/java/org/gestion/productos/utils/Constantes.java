package org.gestion.productos.utils;

public final class Constantes {
    public static final int TAMANIO_PAGINA = 10;

    //Nombre campos tabla usuarios
    public static final String TABLA_PRODUCTO = "productos";
    public static final String CAMPO_PRODUCTO_ID = "id";
    public static final String CAMPO_PRODUCTO_NOMBRE = "nombre";
    public static final String CAMPO_PRODUCTO_CODIGO = "codigo";
    public static final String CAMPO_PRODUCTO_DESCRIPCION = "descripcion";
    public static final String CAMPO_PRODUCTO_PRECIO = "precio";
    public static final String CAMPO_PRODUCTO_STOCK = "stock";
    public static final String CAMPO_PRODUCTO_FECHA_REGISTRO = "fecha_registro";
    public static final String CAMPO_PRODUCTO_FECHA_MODIFICACION = "fecha_modificacion";
    public static final String CAMPO_PRODUCTO_IMAGEN = "imagen";
    public static final String CAMPO_PRODUCTO_CATEGORIA_ID = "categoria_id";
    public static final String CAMPO_PRODUCTO_CATEGORIA_NOMBRE = "categoria";

    //Nombre campos tabla categorias
    public static final String TABLA_CATEGORIA = "categorias";
    public static final String CAMPO_CATEGORIA_ID = "id";
    public static final String CAMPO_CATEGORIA_NOMBRE = "nombre";
    public static final String CAMPO_CATEGORIA_DESCRIPCION = "descripcion";
    public static final String CAMPO_CATEGORIA_ACTIVO = "activo";

    //Nombre campos tabla usuarios
    public static final String TABLA_USUARIO = "usuario";
    public static final String CAMPO_USUARIO_ID = "id";
    public static final String CAMPO_USUARIO_NOMBRE = "nombre";
    public static final String CAMPO_USUARIO_APELLIDOS = "apellidos";
    public static final String CAMPO_USUARIO_EMAIL = "email";
    public static final String CAMPO_USUARIO_TELEFONO = "telefono";
    public static final String CAMPO_USUARIO_DIRECCION = "direccion";
    public static final String CAMPO_USUARIO_USERNAME = "username";
    public static final String CAMPO_USUARIO_PASSWORD = "password";
    public static final String CAMPO_USUARIO_ACTIVO = "activo";
    public static final String CAMPO_USUARIO_ROL_ID = "rol_id";
    public static final String CAMPO_USUARIO_ROL_NOMBRE = "role";

    //Nombre campos tabla role
    public static final String TABLA_ROLE = "roles";
    public static final String CAMPO_ROLE_ID = "id";
    public static final String CAMPO_ROLE_NOMBRE = "nombre";
    public static final String CAMPO_ROLE_DESCRIPCION = "descripcion";

    //Nombre campos tabla pedidoestados
    public static final String TABLA_PEDIDO_ESTADO = "pedidoestados";
    public static final String TABLA_PEDIDO_ESTADO_ID = "id";
    public static final String TABLA_PEDIDO_ESTADO_NOMBRE = "nombre";
    public static final String TABLA_PEDIDO_ESTADO_DESCRIPCION = "descripcion";

    //Nombre campos tabla detallepedidos
    public static final String TABLA_PEDIDO_DETALLE = "detallepedidos";
    public static final String TABLA_PEDIDO_DETALLE_ID = "id";
    public static final String TABLA_PEDIDO_DETALLE_PEDIDO_ID = "pedido_id";
    public static final String TABLA_PEDIDO_DETALLE_PRODUCTO_ID = "producto_id";
    public static final String TABLA_PEDIDO_DETALLE_CANTIDAD = "cantidad";
    public static final String TABLA_PEDIDO_DETALLE_TOTAL = "total";


    //Nombre campos tabla pedidos
    public static final String TABLA_PEDIDO = "pedidos";
    public static final String CAMPO_PEDIDO_ID = "id";
    public static final String CAMPO_PEDIDO_USUARIO_ID = "usuario_id";
    public static final String CAMPO_PEDIDO_PEDIDO_ESTADO_ID = "estado_id";
    public static final String CAMPO_PEDIDO_FECHA_PEDIDO = "fecha_pedido";
    public static final String CAMPO_PEDIDO_TOTAL = "total";

    //Nombre campos ReporteMenusal
    public static final String CAMPO_REPORTE_MENSUAL_MES = "mes";
    public static final String CAMPO_REPORTE_MENSUAL_CANTIDAD = "cantidad";
}
