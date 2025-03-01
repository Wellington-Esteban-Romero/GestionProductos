<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<jsp:include page="layout/header.jsp"/>
<h3 class="text-center">${title}</h3>
<div class="card">
  <div class="card-header bg-primary text-white">
    <h2>Detalle del Producto</h2>
  </div
  <fmt:formatDate value="${fechaRegistro}" pattern="dd-MM-yyyy" var="fechaRegistroFormateada" />
  <fmt:formatDate value="${fechaModificacion}" pattern="dd-MM-yyyy" var="fechaModificacionFormateada" />
  <div class="card-body">
    <ul class="list-group list-group-flush">
      <li class="list-group-item"><strong>ID:</strong> <c:out value="${producto.id}" /></li>
      <li class="list-group-item"><strong>Nombre:</strong> <c:out value="${producto.nombre}" /></li>
      <li class="list-group-item"> <strong>Categoría:</strong>
        <ul class="list-group list-group-flush">
          <li class="list-group-item"><strong>ID:</strong> <c:out value="${producto.categoria.id}" /></li>
          <li class="list-group-item"><strong>Nombre:</strong> <c:out value="${producto.categoria.nombre}" /></li>
          <li class="list-group-item"><strong>Descripción:</strong> <c:out value="${producto.categoria.descripcion}" /></li>
        </ul>
      </li>
      <li class="list-group-item"><strong>Precio:</strong> <c:out value="${producto.precio}" /><c:out value="€"/></li>
      <li class="list-group-item"><strong>Stock:</strong> <c:out value="${producto.stock}" /></li>
      <li class="list-group-item"><strong>Fecha de Registro:</strong> <c:out value="${fechaRegistroFormateada}" /></li>
      <c:if test="${fechaModificacionFormateada eq not '01-01-1980'}">
        <li class="list-group-item"><strong>Fecha de Modificación:</strong> <c:out value="${fechaModificacionFormateada}" /></li>
      </c:if>
    </ul>
  </div>
  <div class="mt-3">
    <a class="btn btn-sm btn-secondary" href="${pageContext.request.contextPath}/productos">Volver</a>
  </div>
</div>
<jsp:include page="layout/footer.jsp"/>