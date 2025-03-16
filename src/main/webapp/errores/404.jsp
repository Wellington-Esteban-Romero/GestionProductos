<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/layout/header.jsp" />
    <h1 class="display-1 text-danger">404</h1>
    <h2 class="text-secondary">Página no encontrada</h2>
    <p class="lead">Lo sentimos, la página que buscas no existe.</p>
<div>
    <a href="<c:url value='/inicio.jsp' />" class="btn btn-primary">Volver al inicio</a>
</div>
<jsp:include page="/layout/footer.jsp" />