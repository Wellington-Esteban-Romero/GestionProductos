<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="layout/header.jsp"/>

<div class="container mt-5">
    <h3 class="text-center text-primary">${title}</h3>
    <div class="card shadow-lg">
        <!-- Imagen del producto -->
        <c:if test="${not empty producto.imagen}">
            <div class="col-md-6 d-flex justify-content-center">
                <img src="${pageContext.request.contextPath}/producto/imagen/${producto.imagen}" class="card-img-top"
                     style="max-width: 100%; border-radius: 15px;" alt="Imagen de ${producto.nombre}">
            </div>
        </c:if>

        <div class="card-header bg-primary text-white text-center">
            <h2 class="mb-0">Detalle del Producto</h2>
        </div>

        <fmt:formatDate value="${fechaRegistro}" pattern="dd-MM-yyyy" var="fechaRegistroFormateada"/>
        <fmt:formatDate value="${fechaModificacion}" pattern="dd-MM-yyyy" var="fechaModificacionFormateada"/>

        <div class="card-body">
            <ul class="list-group list-group-flush">
                <li class="list-group-item"><strong>ID:</strong> <c:out value="${producto.id}"/></li>
                <li class="list-group-item"><strong>Nombre:</strong> <c:out value="${producto.nombre}"/></li>
                <li class="list-group-item"><strong>Categoría:</strong>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item"><strong>ID:</strong> <c:out value="${producto.categoria.id}"/></li>
                        <li class="list-group-item"><strong>Nombre:</strong> <c:out
                                value="${producto.categoria.nombre}"/></li>
                        <li class="list-group-item"><strong>Descripción:</strong> <c:out
                                value="${producto.categoria.descripcion}"/></li>
                    </ul>
                </li>
                <li class="list-group-item"><strong>Precio:</strong> <c:out value="${producto.precio}"/> <c:out
                        value="€"/></li>
                <li class="list-group-item"><strong>Stock:</strong> <c:out value="${producto.stock}"/></li>
                <li class="list-group-item"><strong>Fecha de Registro:</strong> <c:out
                        value="${fechaRegistroFormateada}"/></li>
                <c:if test="${fechaModificacionFormateada ne '01-01-1980'}">
                    <li class="list-group-item"><strong>Fecha de Modificación:</strong> <c:out
                            value="${fechaModificacionFormateada}"/></li>
                </c:if>
            </ul>
        </div>

        <div class="card-footer">
            <a class="btn btn-sm btn-secondary" href="${pageContext.request.contextPath}/productos">Volver</a>
        </div>
    </div>
</div>

<jsp:include page="layout/footer.jsp"/>
