<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<jsp:include page="layout/header.jsp" />
    <h3 class="text-center">${title}</h3>
    <c:if test="${not empty sessionScope.mensajeBienvenida}">
        <div id="alerta-bienvenida" class="alert alert-info">Hola <c:out value="${sessionScope.usuario.username}"/>, Has iniciado sesión correctamente</div>
        <script>
            setTimeout(function() {
                var alerta = document.getElementById('alerta-bienvenida');
                if (alerta) {
                    alerta.style.display = 'none';
                }
            }, 3000);
        </script>
        <c:remove var="mensajeBienvenida" scope="session" />
    </c:if>
    <!-- Sección de estadísticas -->
    <h2 class="mt-4">Estadísticas 📊</h2>
    <p><strong>Total de productos registrados:</strong> ${totalProductos}
    </p>

    <!-- Sección de últimos productos -->
    <h2 class="mt-4">Últimos 5 Productos Agregados 🆕</h2>
    <table class="table table-striped">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Categoría</th>
            <th>Precio</th>
            <th>Fecha registro</th>
        </tr>
        <c:choose>
            <c:when test="${!empty ultimosProductos}">
                <c:forEach items="${ultimosProductos}" var="p">
                    <tr>
                        <td>${p.id}</td>
                        <td>${p.nombre}</td>
                        <td>${p.categoria.nombre}</td>
                        <td>${p.precio}<c:out value="€"/></td>
                        <td>${p.fechaRegistro.format(DateTimeFormatter.ofPattern('dd-MM-yyyy'))}</td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="4">No hay productos recientes</td>
                </tr>
            </c:otherwise>
        </c:choose>
    </table>
<jsp:include page="layout/footer.jsp" />
