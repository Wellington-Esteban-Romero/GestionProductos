<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Formulario Producto</title>
    <c:import url="views/links.jsp"/>
</head>
<body>
<c:import url="views/navbar.jsp"/>
<form action="<c:out value="${pageContext.request.contextPath}"/>/productos/form" method="post">
    <h1 class="text-center">Formulario Producto!</h1>
    <div class="row mb-3">
        <label for="nombre" class="col-form-label col-sm-2">Nombre</label>
        <div class=col-sm-4>
            <input type="text" class="form-control" name="nombre" id="nombre" value="${producto.nombre}">
        </div>
        <c:if test="${errores != null && not empty errores.nombre}">
            <div style="color:red;">${errores.nombre}</div>
        </c:if>
    </div>
    <div class="row mb-3">
        <label for="precio" class="col-form-label col-sm-2">Precio</label>
        <div class=col-sm-4>
            <input type="number" class="form-control" name="precio" id="precio"
                   value="${producto.precio > 0? producto.precio : ""}">
        </div>
        <c:if test="${errores != null && not empty errores.precio}">
            <div style="color:red;">${errores.precio}</div>
        </c:if>
    </div>
    <div class="row mb-3">
        <label for="fecha_registro" class="col-form-label col-sm-2">Fecha Registro</label>
        <div class=col-sm-4>
            <input type="date" class="form-control" name="fecha_registro" id="fecha_registro"
                   value="${producto.fechaRegistro != null ? producto.fechaRegistro.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : ""}">
        </div>
        <c:if test="${errores != null && not empty errores.fecha_registro}">
            <div style="color:red;">${errores.fecha_registro}</div>
        </c:if>
    </div>
    <div class="row mb-3">
        <label for="categoria" class="col-form-label col-sm-2">Categoria</label>
        <div class="col-sm-4">
            <select name="categoria" id="categoria" class="form-select">
                <option value="">--- Selecionar categoria ---</option>
                <c:forEach items="${categorias}" var="c">
                    <option value="${c.id}" ${c.id.equals(producto.categoria.id)? "selected" : ""}>${c.nombre}</option>
                </c:forEach>
            </select>
        </div>
        <c:if test="${errores != null && not empty errores.cateegorias}">
            <div style="color:red;">${errores.categorias}</div>
        </c:if>
    </div>
    <div class="mt-3">
        <input type="submit" class="btn btn-primary"
               value="${(producto.id == null &&  producto.id <= 0)? "Crear" : "Editar"}">
    </div>
    <input type="hidden" name="id" value="${producto.id}">
</form>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
