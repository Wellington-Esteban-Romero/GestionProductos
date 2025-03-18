<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="layout/header.jsp"/>
<div class="vh-100 d-flex justify-content-center align-items-center">
    <div class="card p-4 shadow-lg" style="width: 30rem;">
        <c:if test="${errores != null && not empty errores}">
            <ul class="alert alert-danger container px-5">
                <c:forEach items="${errores.values()}" var="error">
                    <li style="list-style-type: none;">${error}</li>
                </c:forEach>
            </ul>
        </c:if>
        <h3 class="text-center mb-4">${title}</h3>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="mb-3">
                <label for="username" class="form-label">Usuario</label>
                <input type="text" class="form-control" name="username" id="username"
                       minlength="3" maxlength="12" value="${username}">
            </div>
            <div class="mb-3 position-relative">
                <label for="password" class="form-label">Contraseña</label>
                <div class="input-group">
                    <input type="password" class="form-control" name="password" id="password">
                    <button type="button" class="btn btn-outline-secondary" id="togglePassword">
                        <i class="bi bi-eye-fill"></i>
                    </button>
                </div>
            </div>
            <div class="d-grid mb-2">
                <input type="submit" class="btn btn-primary" value="Iniciar Sesión">
            </div>
            <div class="text-center">
                ¿No registrado? <a href="${pageContext.request.contextPath}/registrar">Regístrate aquí</a>
            </div>
            <div class="text-center mt-3">
                <a href="${pageContext.request.contextPath}/perfil/invitado" class="btn btn-outline-secondary">
                    Entrar como invitado
                </a>
            </div>
        </form>
    </div>
</div>
<jsp:include page="layout/footer.jsp"/>
<script>
    document.getElementById('togglePassword').addEventListener('click', function () {
        const passwordField = document.getElementById('password');
        const icon = this.querySelector('i');
        if (passwordField.type === 'password') {
            passwordField.type = 'text';
            icon.classList.remove('bi-eye-fill');
            icon.classList.add('bi-eye-slash-fill');
        } else {
            passwordField.type = 'password';
            icon.classList.remove('bi-eye-slash-fill');
            icon.classList.add('bi-eye-fill');
        }
    });
</script>