<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="layout/header.jsp"/>

<div class="d-flex justify-content-center align-items-center">
    <div class="card mb-3 p-4 shadow-lg" style="width: 30rem;">
        <h3 class="text-center mb-4">${title}</h3>

        <form action="${pageContext.request.contextPath}/register" method="post">
            <div class="mb-3">
                <label for="nombre" class="form-label">Nombre</label>
                <input type="text" class="form-control" name="nombre" id="nombre" required>
            </div>
            <div class="mb-3">
                <label for="apellidos" class="form-label">Apellidos</label>
                <input type="text" class="form-control" name="apellidos" id="apellidos" required>
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Correo Electrónico</label>
                <input type="email" class="form-control" name="email" id="email" required>
            </div>
            <div class="mb-3">
                <label for="telefono" class="form-label">Número de Teléfono</label>
                <input type="tel" class="form-control" name="telefono" id="telefono" pattern="[0-9]{6}"
                       placeholder="Ingrese su número de teléfono" required>
            </div>
            <div class="mb-3">
                <label for="direccion" class="form-label">Dirección</label>
                <textarea class="form-control" name="direccion" id="direccion" rows="2"
                          placeholder="Ingrese su dirección" required></textarea>
            </div>
            <div class="mb-3">
                <label for="username" class="form-label">Usuario</label>
                <input type="text" class="form-control" name="username" id="username"
                       minlength="3" maxlength="12" value="${username}" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Contraseña</label>
                <input type="password" class="form-control" name="password" id="password" required>
            </div>
            <div class="mb-3">
                <label for="confirmPassword" class="form-label">Confirmar Contraseña</label>
                <input type="password" class="form-control" name="confirmPassword" id="confirmPassword" required>
            </div>
            <div class="d-grid">
                <input type="submit" class="btn btn-primary" value="Registrar Usuario">
            </div>
        </form>
    </div>
</div>

<jsp:include page="layout/footer.jsp"/>