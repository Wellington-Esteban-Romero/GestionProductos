<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*" %>
<%@ page import="org.gestion.productos.models.*" %>
<%
    List<Producto> productos = (List<Producto>) request.getAttribute("productos");
    Optional<String> username = (Optional<String>) request.getAttribute("username");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Listado de productos</title>
    <c:import url="views/links.jsp" />
</head>
<body>
<c:import url="views/navbar.jsp" />
<div class="container mt-4">
    <h1>Listado de productos!</h1>
    <% if (username.isPresent()) {%>
    <div>Hola <%=username.get()%>, Bienvenido</div>
    <%}%>
    <table class="table table-striped">
        <tr>
            <th>id</th>
            <th>Nombre</th>
            <th>Tipo</th>
            <th>Fecha registro</th>
            <% if (username.isPresent()) {%>
            <th>Precio</th>
            <th>Agregar</th>
            <%}%>
        </tr>
        <% for (Producto producto : productos) {%>
        <tr>
            <td><%=producto.getId()%></td>
            <td><%=producto.getNombre()%></td>
            <td><%=producto.getCategoria().getNombre()%></td>
            <td><%=producto.getFechaRegistro()%></td>
            <% if (username.isPresent()) {%>
            <td><%=producto.getPrecio()%>
            </td>
            <td><a href="<%=request.getContextPath()%>/carro/agregar?id=<%=producto.getId()%>">Agregar al carro</a></td>
            <%}%>
        </tr>
        <%}%>
    </table>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>