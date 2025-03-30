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
            <th>Estado</th>
            <c:if test="${username.present}">
                <th></th>
                <th></th>
                <th></th>
            </c:if>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${categorias}" var="categoria">
            <tr class="align-middle">
                <td><strong>${categoria.id}</strong></td>
                <td>${categoria.nombre}</td>
                <td>${categoria.descripcion}</td>
                <td>
                            <span class="badge ${categoria.activo ? 'bg-success' : 'bg-danger'}">
                                    ${categoria.activo ? 'Activo' : 'Desactivado'}
                            </span>
                </td>
                <c:if test="${username.present}">
                    <td>
                        <a class="btn btn-sm btn-warning w-100 fw-bold"
                           href="${pageContext.request.contextPath}/categorias/form?id=${categoria.id}">
                            ✏️ Editar
                        </a>
                    </td>
                    <td>
                        <a class="btn btn-sm btn-primary w-100 fw-bold"
                           href="${pageContext.request.contextPath}/categorias/eliminar?id=${categoria.id}&active=1">
                            ✅ Activar
                        </a>
                    </td>
                    <td>
                        <a class="btn btn-sm btn-danger w-100 fw-bold"
                           onclick="return confirm('⚠️ ¿Está seguro que quiere desactivar esta categoría?');"
                           href="${pageContext.request.contextPath}/categorias/eliminar?id=${categoria.id}&active=0">
                            🚫 Desactivar
                        </a>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<jsp:include page="layout/footer.jsp"/>
