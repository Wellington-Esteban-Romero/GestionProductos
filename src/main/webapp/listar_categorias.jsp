<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="layout/header.jsp"/>
<h3 class="text-center">${title}</h3>
<table class="table table-striped">
    <tr>
        <th>id</th>
        <th>Nombre</th>
        <th>Descripción</th>
    </tr>
    <c:forEach items="${categorias}" var="categoria">
        <tr>
            <td>${categoria.id}</td>
            <td>${categoria.nombre}></td>
            <td>${categoria.descripcion}</td>
        </tr>
    </c:forEach>
</table>
<jsp:include page="layout/footer.jsp"/>