<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="layout/header.jsp" />
    <h3 class="text-center">${title}</h3>
    <c:if test="${not empty sessionScope.username}">
        <div class="alert alert-info">Hola <c:out value="${username}"/>, Has iniciado sesión correctamente</div>
        <div class="mt-3 mb-2">
        </div>

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
            <th>Categoria</th>
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
                        <td><c:out value="$"/>${p.precio}</td>
                        <td>${p.fechaRegistro}</td>
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
