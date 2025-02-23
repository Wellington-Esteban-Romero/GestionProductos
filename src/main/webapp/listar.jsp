<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="layout/header.jsp"/>
<h3 class="text-center">${title}</h3>
<c:if test="${username.isPresent()}">
    <div class="alert alert-info">Hola <c:out value="${username.get()}"/>, Bienvenido!</div>
    <div class="mt-3 mb-2">
        <a class="btn btn-primary" href="<c:out value="${pageContext.request.contextPath}"/>/productos/form">Crear
            [+]</a>
    </div>
    <div class="mt-3 mb-2">
        <form class="mb-3" action="${pageContext.request.contextPath}/productos/buscar" method="post">
            <div class="row">
                <div class="form-group col-md-3 col-sm-6">
                    <label for="buscarNombre" class="col-form-label">Buscar producto</label>
                    <input type="text" class="form-control" name="buscarNombre" id="buscarNombre"
                           placeholder="Nombre del producto">
                </div>
                <div class="form-group col-md-3 col-sm-6">
                    <label for="precioMin" class="col-form-label">Precio Mínimo (€)</label>
                    <input type="number" class="form-control" id="precioMin" name="precioMin"
                           placeholder="Precio Mínimo (€)" min="0">
                </div>
                <div class="form-group col-md-3 col-sm-6">
                    <label for="precioMax" class="col-form-label">Precio Máximo (€)</label>
                    <input type="number" class="form-control" id="precioMax" name="precioMax"
                           placeholder="Precio Máximo (€)" min="0">
                </div>
            </div>
            <div class="col-sm-2 mt-3">
                <div>
                    <input class="btn btn-success" type="submit" value="Buscar">
                </div>
            </div>
        </form>
    </div>
</c:if>
<c:choose>
    <c:when test="${empty productos}">
        <div class="alert alert-secondary">No existe el producto</div>
    </c:when>
    <c:otherwise>
        <table class="table table-hover table-striped">
            <tr>
                <th>id</th>
                <th>Nombre</th>
                <th>Tipo</th>
                <th>Fecha registro</th>
                <c:if test="${username.present}">
                    <th>Precio</th>
                    <th>Agregar</th>
                    <th>Editar</th>
                    <th>Eliminar</th>
                </c:if>
            </tr>
            <c:forEach items="${productos}" var="producto">
                <tr>
                    <td>
                        <a style="text-decoration:none;"
                           href="${pageContext.request.contextPath}/productos/detalles?id=<c:out value="${producto.id}"/>">
                            <c:out value="${producto.id}"/>
                        </a>
                    </td>
                    <td><c:out value="${producto.nombre}"/>
                    </td>
                    <td><c:out value="${producto.categoria.nombre}"/>
                    </td>
                    <td><c:out value="${producto.fechaRegistro}"/>
                    </td>
                    <c:if test="${username.present}">
                        <td>$<c:out value="${producto.precio}"/>
                        </td>
                        <td>
                            <a class="btn btn-sm btn-primary"
                               href="<c:out value="${pageContext.request.contextPath}"/>/carro/agregar?id=<c:out value="${producto.id}"/>">Agregar
                                al
                                carro</a></td>
                        <td><a class="btn btn-sm btn-success"
                               href="<c:out value="${pageContext.request.contextPath}"/>/productos/form?id=<c:out value="${producto.id}"/>">Editar</a>
                        </td>
                        <td><a class="btn btn-sm btn-danger"
                               onclick="return confirm('¿Esta seguro que quiere eliminar?');"
                               href="<c:out value="${pageContext.request.contextPath}"/>/productos/eliminar?id=<c:out value="${producto.id}"/>">Eliminar</a>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>
<jsp:include page="layout/footer.jsp"/>