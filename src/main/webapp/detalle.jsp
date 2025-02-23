<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="layout/header.jsp"/>
<h3 class="text-center">${title}</h3>
<div class="card">
  <div class="card-header bg-primary text-white">
    <h2>Detalle del Producto</h2>
  </div>
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
      <li class="list-group-item"><strong>Fecha de Registro:</strong> <c:out value="${producto.fechaRegistro}" /></li>
    </ul>
  </div>
</div>
<jsp:include page="layout/footer.jsp"/>