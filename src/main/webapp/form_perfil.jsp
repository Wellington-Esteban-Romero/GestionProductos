<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="layout/header.jsp"/>
<div class="row justify-content-center">
    <div class="col-md-6">
        <div class="card">
            <div class="card-header text-center bg-primary text-white">
                <h3>Perfil del Usuario</h3>
            </div>
            <div class="card-body">
                <form action="<c:out value="${pageContext.request.contextPath}"/>/perfil/form" method="post">
                    <div class="mb-3">
                        <label for="username" class="form-label">Username:</label>
                        <input type="text" id="username" name="username" class="form-control"
                               value="${username.username}" disabled="disabled">
                    </div>
                    <div class="mb-3">
                        <label for="nombre" class="form-label">Nombre</label>
                        <input type="text" id="nombre" name="nombre" class="form-control" value="${username.nombre}">
                    </div>
                    <div class="mb-3">
                        <label for="apellidos" class="form-label">Apellidos</label>
                        <input type="text" id="apellidos" name="apellidos" class="form-control"
                               value="${username.apellidos}">
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Correo Electrónico</label>
                        <input type="email" id="email" name="email" class="form-control" value="${username.email}">
                    </div>

                    <div class="mb-3">
                        <label for="telefono" class="form-label">Teléfono</label>
                        <input type="text" id="telefono" name="telefono" class="form-control"
                               value="${username.telefono}">
                    </div>
                    <div class="mb-3 form-check">
                        <input type="checkbox" class="form-check-input" id="cambiarContrasenia"
                               onclick="mostrarCamposContrasenia()">
                        <label class="form-check-label" for="cambiarContrasenia">Cambiar Contraseña</label>
                    </div>

                    <div id="camposContrasenia" style="display: none;">
                        <div class="mb-3">
                            <label for="contraseniaActual" class="form-label">Contraseña Actual</label>
                            <input type="password" id="contraseniaActual" name="contraseniaActual" class="form-control">
                        </div>
                        <div class="mb-3">
                            <label for="nuevaContrasenia" class="form-label">Nueva Contraseña</label>
                            <input type="password" id="nuevaContrasenia" name="nuevaContrasenia" class="form-control">
                        </div>
                        <div class="mb-3">
                            <label for="confirmarContrasenia" class="form-label">Confirmar Contraseña</label>
                            <input type="password" id="confirmarContrasenia" name="confirmarContrasenia"
                                   class="form-control">
                        </div>
                        <div class="text-center">
                            <button type="submit"
                                    formaction="<c:out value="${pageContext.request.contextPath}"/>/perfil/cambiarContrasenia"
                                    class="btn btn-warning">Actualizar Contraseña
                            </button>
                        </div>
                    </div>
                    <div class="text-center mt-3">
                        <button type="submit" class="btn btn-success">Actualizar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    function mostrarCamposContrasenia() {
        var checkBox = document.getElementById("cambiarContrasenia");
        var campos = document.getElementById("camposContrasenia");
        if (checkBox.checked) {
            campos.style.display = "block";
        } else {
            campos.style.display = "none";
        }
    }
</script>
<jsp:include page="layout/footer.jsp"/>