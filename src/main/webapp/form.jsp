<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<jsp:include page="layout/header.jsp"/>
<script>
    function previewImage(event) {
        let reader = new FileReader();
        reader.onload = function () {
            let output = document.getElementById('preview');
            output.src = reader.result;
            output.style.display = 'block';
        };
        debugger
        reader.readAsDataURL(event.target.files[0]);
    }
</script>

<h3 class="text-center">${title}</h3>

<div class="container">
    <div class="row">
        <!-- Formulario a la izquierda -->
        <div class="col-md-6">
            <form action="<c:out value="${pageContext.request.contextPath}"/>/productos/form" method="post"
                  enctype="multipart/form-data">
                <div class="mb-3">
                    <label for="nombre" class="form-label">Nombre</label>
                    <input type="text" class="form-control ${not empty errores.nombre ? 'is-invalid' : ''}"
                           name="nombre" id="nombre" value="${producto.nombre}">
                    <c:if test="${errores != null && not empty errores.nombre}">
                        <div class="text-danger">${errores.nombre}</div>
                    </c:if>
                </div>

                <div class="mb-3">
                    <label for="precio" class="form-label">Precio</label>
                    <input type="number" step="any" class="form-control ${not empty errores.precio ? 'is-invalid' : ''}"
                           name="precio" id="precio" value="${producto.precio > 0.0? producto.precio : ''}">
                    <c:if test="${errores != null && not empty errores.precio}">
                        <div class="text-danger">${errores.precio}</div>
                    </c:if>
                </div>

                <div class="mb-3">
                    <label for="stock" class="form-label">Stock</label>
                    <input type="number" class="form-control ${not empty errores.stock ? 'is-invalid' : ''}"
                           name="stock" id="stock" value="${producto.stock >=0? producto.stock : ''}">
                    <c:if test="${errores != null && not empty errores.stock}">
                        <div class="text-danger">${errores.stock}</div>
                    </c:if>
                </div>

                <div class="mb-3">
                    <label for="fecha_registro" class="form-label">Fecha Registro</label>
                    <input type="date" class="form-control ${not empty errores.fecha_registro ? 'is-invalid' : ''}"
                           name="fecha_registro" id="fecha_registro"
                           value="${producto.fechaRegistro != null ? producto.fechaRegistro.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : ''}">
                    <c:if test="${errores != null && not empty errores.fecha_registro}">
                        <div class="text-danger">${errores.fecha_registro}</div>
                    </c:if>
                </div>

                <div class="mb-3">
                    <label for="categoria" class="form-label">Categoría</label>
                    <select name="categoria" id="categoria"
                            class="form-select ${not empty errores.categoria ? 'is-invalid' : ''}">
                        <option value="">--- Seleccionar categoría ---</option>
                        <c:forEach items="${categorias}" var="c">
                            <option value="${c.id}" ${c.id.equals(producto.categoria.id)? "selected" : ""}>${c.nombre}</option>
                        </c:forEach>
                    </select>
                    <c:if test="${errores != null && not empty errores.categoria}">
                        <div class="text-danger">${errores.categoria}</div>
                    </c:if>
                </div>

                <div class="mb-3">
                    <label for="descripcion" class="form-label">Descripción</label>
                    <textarea class="form-control" rows="4" maxlength="255" name="descripcion"
                              id="descripcion">${producto.descripcion}</textarea>
                </div>

                <div class="mb-3">
                    <input type="file" class="form-control" name="imagen" accept="image/*"
                           onchange="previewImage(event)">
                </div>

                <div class="mt-3">
                    <input type="submit" class="btn btn-primary" value="${(producto.id == null)? 'Crear' : 'Editar'}">
                </div>
                <input type="hidden" name="id" value="${producto.id}">
            </form>
        </div>
        <div class="col-md-6 d-flex flex-column align-items-center">
                <img id="preview" class="img-fluid" style="max-width: 100%;"
                     src="${pageContext.request.contextPath}/producto/imagen/${producto.imagen}">
        </div>
    </div>
</div>

<jsp:include page="layout/footer.jsp"/>