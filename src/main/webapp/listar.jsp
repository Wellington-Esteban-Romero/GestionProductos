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
    <c:import url="views/links.jsp"/>
</head>
<body>
<c:import url="views/navbar.jsp"/>
<div class="container mt-4">
    <h1 class="text-center">¡Listado de productos!</h1>
    <c:if test="${username.isPresent()}">
        <div>Hola <c:out value="${username.get()}"/>, Bienvenido</div>
        <div class="mt-3">
            <a class="btn btn-primary" href="<c:out value="${pageContext.request.contextPath}"/>/productos/form">Crear [+]</a>
        </div>

    </c:if>
    <table class="table table-striped">
        <tr>
            <th>id</th>
            <th>Nombre</th>
            <th>Tipo</th>
            <th>Fecha registro</th>
            <c:if test="${username.present}">
                <th>Precio</th>
                <th>Agregar</th>
                <th>Editar</th>
                <th>Eliminar</th>
            </c:if>
        </tr>
        <c:forEach items="${productos}" var="producto">
            <tr>
                <td><c:out value="${producto.id}"/>
                </td>
                <td><c:out value="${producto.nombre}"/>
                </td>
                <td><c:out value="${producto.categoria.nombre}"/>
                </td>
                <td><c:out value="${producto.fechaRegistro}"/>
                </td>
                <c:if test="${username.present}">
                    <td>$<c:out value="${producto.precio}"/>
                    </td>
                    <td>
                        <a href="<c:out value="${pageContext.request.contextPath}"/>/carro/agregar?id=<c:out value="${producto.id}"/>">Agregar
                            al
                            carro</a></td>
                    <td><a href="<c:out value="${pageContext.request.contextPath}"/>/productos/form?id=<c:out value="${producto.id}"/>">Editar</a></td>
                    <td><a onclick="return confirm('¿Esta seguro que quiere eliminar?');"
                           href="<c:out value="${pageContext.request.contextPath}"/>/productos/eliminar?id=<c:out value="${producto.id}"/>">Eliminar</a></td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>