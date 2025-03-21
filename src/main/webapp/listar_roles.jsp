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
    <c:forEach items="${roles}" var="role">
        <tr>
            <td>
                <a style="text-decoration:none;"
                   href="${pageContext.request.contextPath}/roles/detalles?id=<c:out value="${role.id}"/>">
                    <c:out value="${role.id}"/>
                </a>
            <td>${role.nombre}</td>
            <td>${role.descripcion}</td>
        </tr>
    </c:forEach>
</table>
<jsp:include page="layout/footer.jsp"/>