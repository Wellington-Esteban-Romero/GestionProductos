<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<jsp:include page="layout/header.jsp"/>
<h3 class="text-center">${title}</h3>

<div class="container">
    <div class="row">
        <div class="col-md-6">
            <form action="<c:out value="${pageContext.request.contextPath}"/>/categorias/form" method="post">
                <div class="mb-3">
                    <label for="nombre" class="form-label">Nombre</label>
                    <input type="text" class="form-control ${not empty errores.nombre ? 'is-invalid' : ''}"
                           name="nombre" id="nombre" value="${categoria.nombre}">
                    <c:if test="${errores != null && not empty errores.nombre}">
                        <div class="text-danger">${errores.nombre}</div>
                    </c:if>
                </div>

                <div class="mb-3">
                    <label for="descripcion" class="form-label">Descripción</label>
                    <textarea class="form-control" rows="4" maxlength="255" name="descripcion"
                              id="descripcion">${categoria.descripcion}</textarea>
                </div>

                <div class="mt-3">
                    <input type="submit" class="btn btn-primary" value="${(categoria.id == null)? 'Crear' : 'Editar'}">
                </div>
                <input type="hidden" name="id" value="${categoria.id}">
            </form>
        </div>
    </div>
</div>

<jsp:include page="layout/footer.jsp"/>