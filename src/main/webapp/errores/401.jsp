<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/layout/header.jsp" />
    <h1 class="display-1 text-danger">🚫 401</h1>
    <h2 class="text-secondary">Acceso Denegado</h2>
    <p class="lead">No tienes permisos para ver esta página. Por favor, inicia sesión o contacta con el administrador.</p>
<div>
    <a href="<c:url value='/login' />" class="btn btn-danger">Ir a Inicio de Sesión</a>
</div>

<jsp:include page="/layout/footer.jsp" />