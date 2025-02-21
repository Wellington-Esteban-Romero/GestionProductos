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
    <title>Formulario Producto</title>
    <c:import url="views/links.jsp"/>
</head>
<body>
<c:import url="views/navbar.jsp" />
<form action="<%=request.getContextPath()%>/productos/form" method="post">
    <h1 class="text-center">Formulario Producto!</h1>
    <div class="row mb-3">
        <label for="nombre" class="col-form-label col-sm-2">Nombre</label>
        <div class=col-sm-4>
            <input type="text" class="form-control" name="nombre" id="nombre">
        </div>
    </div>
    <div class="row mb-3">
        <label for="precio" class="col-form-label col-sm-2">Precio</label>
        <div class=col-sm-4>
            <input type="number" class="form-control" name="precio" id="precio">
        </div>
    </div>
    <div class="row mb-3">
        <label for="fecha_registro" class="col-form-label col-sm-2">Fecha Registro</label>
        <div class=col-sm-4>
            <input type="date" class="form-control" name="fecha_registro" id="fecha_registro">
        </div>
    </div>
    <div class="row mb-3">
        <label for="categoria" class="col-form-label col-sm-2">Categoria</label>
        <div class="col-sm-4">
            <select name="categoria" id="categoria" class="form-select">
                <option value="">--- Selecionar categoria ---</option>
                <% for (Categoria c : categorias) { %>
                <option value="<%=c.getId()%>"><%=c.getNombre()%>
                </option>
                <%}%>
            </select>
        </div>
    </div>
    <div>
        <input type="submit" class="btn btn-primary" value="Crear">
    </div>
</form>
</body>
</html>