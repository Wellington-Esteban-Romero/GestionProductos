<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="layout/header.jsp"/>

<div class="d-flex justify-content-center align-items-center">
    <div class="card mb-3 p-4 shadow-lg" style="width: 30rem;">
        <h3 class="text-center mb-4">${title}</h3>

        <form action="${pageContext.request.contextPath}/registrar" method="post">
            <div class="mb-3">
                <label for="nombre" class="form-label">Nombre</label>
                <input type="text" class="form-control" name="nombre" id="nombre" value="${usuario.nombre}">
                <c:if test="${errores != null && not empty errores.nombre}">
                    <div class="text-danger">${errores.nombre}</div>
                </c:if>
            </div>
            <div class="mb-3">
                <label for="apellidos" class="form-label">Apellidos</label>
                <input type="text" class="form-control" name="apellidos" id="apellidos" value="${usuario.apellidos}">
                <c:if test="${errores != null && not empty errores.apellidos}">
                    <div class="text-danger">${errores.apellidos}</div>
                </c:if>
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Correo Electrónico</label>
                <input type="email" class="form-control" name="email" id="email" value="${usuario.email}">
                <c:if test="${errores != null && not empty errores.email}">
                    <div class="text-danger">${errores.email}</div>
                </c:if>
            </div>
            <div class="mb-3">
                <div class="flex-grow-1">
                    <label for="telefono" class="form-label">Número de Teléfono</label>
                    <div class="input-group">
                        <button class="btn btn-outline-secondary" type="button" id="btn-prefijo">
                            <img src="${pageContext.request.contextPath}/icons/es.png" alt="España" width="20"> +34
                        </button>
                        <input type="tel" class="form-control" name="telefono" id="telefono" value="${usuario.telefono}"
                               maxlength="9" placeholder="Ingrese su número de teléfono">
                    </div>
                    <c:if test="${errores != null && not empty errores.telefono}">
                        <div class="text-danger">${errores.telefono}</div>
                    </c:if>
                </div>
            </div>
            <div class="mb-3">
                <label for="direccion" class="form-label">Dirección</label>
                <textarea class="form-control" name="direccion" id="direccion" rows="2"
                          placeholder="Ingrese su dirección" maxlength="80"
                          style="resize: none">${usuario.direccion}</textarea>
                <c:if test="${errores != null && not empty errores.direccion}">
                    <div class="text-danger">${errores.direccion}</div>
                </c:if>
            </div>
            <div class="mb-3">
                <label for="username" class="form-label">Usuario</label>
                <input type="text" class="form-control" name="username" id="username"
                       minlength="3" maxlength="12" value="${usuario.username}">
                <c:if test="${errores != null && not empty errores.usuario}">
                    <div class="text-danger">${errores.usuario}</div>
                </c:if>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Contraseña</label>
                <div class="input-group">
                    <input type="password" class="form-control" name="password" id="password"
                           value="${usuario.password}">
                    <button class="btn btn-outline-secondary" type="button" id="togglePassword">
                        <i class="bi bi-eye-fill"></i>
                    </button>
                </div>
                <c:if test="${errores != null && not empty errores.contrasenia}">
                    <div class="text-danger">${errores.contrasenia}</div>
                </c:if>
            </div>
            <div class="mb-3">
                <label for="repetir_password" class="form-label">Confirmar Contraseña</label>
                <input type="password" class="form-control" name="repetir_password" id="repetir_password">
                <c:if test="${errores != null && not empty errores.repetir_password}">
                    <div class="text-danger">${errores.repetir_password}</div>
                </c:if>
            </div>
            <div class="d-grid">
                <input type="submit" class="btn btn-primary" value="Registrar Usuario">
            </div>
            <div class="text-center mt-3">
                <a href="${pageContext.request.contextPath}/login" class="text-decoration-none">
                    ¿Ya tienes una cuenta? Inicia sesión aquí
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