<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*" %>
<%@ page import="org.gestion.productos.models.*" %>
<%
    List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Listado de categorías</title>
    <c:import url="views/links.jsp" />
</head>
<body>
<c:import url="views/navbar.jsp" />
<div class="container mt-4">
    <h1 class="text-center">Listado de categorías!</h1>
    <table class="table table-striped">
        <tr>
            <th>id</th>
            <th>Nombre</th>
            <th>Descripción</th>
        </tr>
        <% for (Categoria categoria : categorias) {%>
        <tr>
            <td><%=categoria.getId()%></td>
            <td><%=categoria.getNombre()%></td>
            <td><%=categoria.getDescripcion()%></td>
        </tr>
        <%}%>
    </table>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>