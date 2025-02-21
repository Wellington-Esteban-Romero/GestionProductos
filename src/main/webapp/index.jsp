<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="org.gestion.productos.models.Producto" %>
<%@ page import="java.util.List" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Inicio - Gestión de Productos</title>
    <c:import url="views/links.jsp" />
</head>
<body>
<c:import url="views/navbar.jsp" />
<div class="container mt-4">
    <h1 class="text-center">Bienvenido a la Gestión de Productos</h1>
    <!-- Sección de estadísticas -->
    <h2 class="mt-4">Estadísticas 📊</h2>
    <p><strong>Total de productos registrados:</strong> <%= request.getAttribute("totalProductos") %></p>

    <!-- Sección de últimos productos -->
    <h2 class="mt-4">Últimos 5 Productos Agregados 🆕</h2>
    <table class="table table-striped">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Categoria</th>
            <th>Precio</th>
            <th>Fecha registro</th>
        </tr>
        <%
            List<Producto> ultimosProductos = (List<Producto>) request.getAttribute("ultimosProductos");
            if (ultimosProductos != null) {
                for (Producto p : ultimosProductos) {
        %>
        <tr>
            <td><%= p.getId() %></td>
            <td><%= p.getNombre() %></td>
            <td><%= p.getCategoria().getNombre() %></td>
            <td>$<%= p.getPrecio() %></td>
            <td><%= p.getFechaRegistro() %></td>
        </tr>
        <%
            }
        } else {
        %>
        <tr><td colspan="4">No hay productos recientes</td></tr>
        <% } %>
    </table>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
