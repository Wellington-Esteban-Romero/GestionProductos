<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="layout/header.jsp"/>

<div class="vh-100 d-flex justify-content-center align-items-center bg-light">
    <div class="card p-4 shadow-lg rounded-4" style="width: 30rem;">

        <c:if test="${errores != null && not empty errores}">
            <div class="alert alert-danger">
                <ul class="list-group list-group-flush">
                    <c:forEach items="${errores.values()}" var="error">
                        <li class="list-group-item border-0 bg-transparent text-danger">${error}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>

        <h3 class="text-center mb-4 text-primary">${title}</h3>

        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="mb-3">
                <label for="username" class="form-label fw-semibold">Usuario</label>
                <input type="text" class="form-control" name="username" id="username"
                       minlength="3" maxlength="12" value="${username}"
                       aria-label="Ingrese su nombre de usuario">
            </div>

            <div class="mb-3 position-relative">
                <label for="password" class="form-label fw-semibold">Contraseña</label>
                <div class="input-group">
                    <input type="password" class="form-control" name="password" id="password"
                           aria-label="Ingrese su contraseña">
                    <button type="button" class="btn btn-outline-dark" id="togglePassword">
                        <i class="bi bi-eye-fill"></i>
                    </button>
                </div>
            </div>

            <div class="d-grid mb-2">
                <input type="submit" class="btn btn-primary btn-lg fw-bold transition" value="Iniciar Sesión">
            </div>

            <div class="text-center">
                <span class="text-muted">¿No registrado?</span>
                <a href="${pageContext.request.contextPath}/registrar" class="text-decoration-none fw-semibold">
                    Regístrate aquí
                </a>
            </div>

            <div class="text-center mt-3">
                <a href="${pageContext.request.contextPath}/perfil/invitado" class="btn btn-outline-secondary">
                    <i class="bi bi-person-fill"></i> Entrar como invitado
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
            icon.classList.replace('bi-eye-fill', 'bi-eye-slash-fill');
        } else {
            passwordField.type = 'password';
            icon.classList.replace('bi-eye-slash-fill', 'bi-eye-fill');
        }
    });
</script>

<style>
    .transition:hover {
        transform: scale(1.05);
        transition: 0.2s ease-in-out;
    }
</style>
