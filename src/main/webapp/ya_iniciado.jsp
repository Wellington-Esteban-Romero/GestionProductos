<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="layout/header.jsp"/>
<%-- Verificar si el usuario está en la sesión --%>
<c:if test="${not empty sessionScope.usuario}">
    <h1>Hola ${sessionScope.usuario.username}, ya has iniciado sesión</h1>
    <p><a class="btn btn-secondary" href="${pageContext.request.contextPath}/">Volver</a></p>
    <p><a class="btn btn-primary" href="${pageContext.request.contextPath}/logout">Cerrar sesión</a></p>
</c:if>
<jsp:include page="layout/footer.jsp"/>

<c:if test="${empty sessionScope.usuario}">
    <c:redirect url="/login.jsp"/>
</c:if>