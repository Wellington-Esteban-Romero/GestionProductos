<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="layout/header.jsp"/>

<h3 class="text-center text-primary">${title}</h3>

<div class="table-responsive">
    <table class="table table-striped table-hover shadow-sm text-center">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Descripción</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${roles}" var="role">
            <tr class="align-middle">
                <td>
                    <a class="text-decoration-none fw-bold text-primary"
                       href="${pageContext.request.contextPath}/roles/detalles?id=${role.id}">
                            ${role.id}
                    </a>
                </td>
                <td>${role.nombre}</td>
                <td>${role.descripcion}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<jsp:include page="layout/footer.jsp"/>