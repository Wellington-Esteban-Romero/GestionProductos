<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="layout/header.jsp"/>
<h3 class="text-center">${title}</h3>
<table class="table table-striped">
    <tr>
        <th>id</th>
        <th>Nombre</th>
        <th>Descripción</th>
        <th>Estado</th>
        <c:if test="${username.present}">
            <th></th>
            <th></th>
            <th></th>
        </c:if>
    </tr>
    <c:forEach items="${categorias}" var="categoria">
        <tr>
            <td>${categoria.id}</td>
            <td>${categoria.nombre}</td>
            <td>${categoria.descripcion}</td>
            <c:if test="${categoria.activo eq true}">
                <td style="color: green;">Activo</td>
            </c:if>
            <c:if test="${categoria.activo eq false}">
                <td style="color: red;">Desactivado</td>
            </c:if>
            <c:if test="${username.present}">
                <td><a class="btn btn-sm btn-success"
                       href="<c:out value="${pageContext.request.contextPath}"/>/categorias/form?id=<c:out value="${categoria.id}"/>">Editar</a>
                </td>
                <td><a class="btn btn-sm btn-primary"
                       href="<c:out value="${pageContext.request.contextPath}"/>/categorias/eliminar?id=<c:out value="${categoria.id}"/>&active=1">Activar</a>
                </td>
                <td><a class="btn btn-sm btn-danger"
                       href="<c:out value="${pageContext.request.contextPath}"/>/categorias/eliminar?id=<c:out value="${categoria.id}"/>&active=0">Desactivar</a>
                </td>
            </c:if>
        </tr>
    </c:forEach>
</table>
<jsp:include page="layout/footer.jsp"/>