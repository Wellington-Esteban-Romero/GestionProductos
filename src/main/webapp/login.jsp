<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="layout/header.jsp"/>
<div class="vh-100 d-flex justify-content-center align-items-center">
    <div class="card p-4 shadow-lg" style="width: 30rem;">
        <c:if test="${errores != null && not empty errores}">
            <ul class="alert alert-danger container px-5">
                <c:forEach items="${errores.values()}" var="error">
                    <li>${error}</li>
                </c:forEach>
            </ul>
        </c:if>
        <h3 class="text-center mb-4">${title}</h3>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input type="text" class="form-control" name="username" id="username"
                       minlength="3" maxlength="12" value="${username}">
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" name="password" id="password">
            </div>
            <div class="d-grid">
                <input type="submit" class="btn btn-primary" value="Iniciar Sesión">
            </div>
        </form>
    </div>
</div>
<jsp:include page="layout/footer.jsp"/>