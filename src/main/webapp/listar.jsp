<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<jsp:include page="layout/header.jsp"/>
<div class="flex-grow-1">
<h3 class="text-center">${title}</h3>
<c:if test="${username.isPresent()}">
    <div class="mt-3 mb-2">
        <form class="mb-3" action="${pageContext.request.contextPath}/productos/buscar" method="post">
            <div class="row">
                <div class="form-group col-md-3 col-sm-6">
                    <label for="buscarNombre" class="col-form-label">Buscar producto</label>
                    <input type="text" class="form-control" name="buscarNombre" id="buscarNombre"
                           placeholder="Nombre del producto">
                </div>
                <div class="form-group col-md-3 col-sm-6">
                    <label for="tipo" class="col-form-label">Buscar categoría</label>
                    <input type="text" class="form-control" name="tipo" id="tipo"
                           placeholder="Nombre categoría">
                </div>
                <div class="form-group col-md-3 col-sm-6">
                    <label for="precioMin" class="col-form-label">Precio Mínimo >=</label>
                    <input type="number" class="form-control" id="precioMin" name="precioMin"
                           placeholder="Precio Mínimo (€)" min="0">
                </div>
                <div class="form-group col-md-3 col-sm-6">
                    <label for="precioMax" class="col-form-label">Precio Máximo <=</label>
                    <input type="number" class="form-control" id="precioMax" name="precioMax"
                           placeholder="Precio Máximo (€)">
                </div>
                <div class="form-group col-md-3 col-sm-6">
                    <label for="fecha_inicio" class="col-form-label">Fecha inicio >=</label>
                    <input type="date" class="form-control" name="fecha_inicio" id="fecha_inicio">
                </div>
                <div class="form-group col-md-3 col-sm-6">
                    <label for="fecha_fin" class="col-form-label">Fecha Fin <=</label>
                    <input type="date" class="form-control" name="fecha_fin" id="fecha_fin">
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
                <th>Categoría</th>
                <th>Fecha registro</th>
                <c:if test="${username.present}">
                    <th>Precio</th>
                    <th></th>
                    <th></th>
                    <th></th>
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
                    <td><c:out value="${producto.fechaRegistro.format(DateTimeFormatter.ofPattern('dd-MM-yyyy'))}"/>
                    </td>
                    <c:if test="${username.present}">
                        <td><c:out value="${producto.precio}"/><c:out value="€"/>
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
        <!-- Paginación -->
        <nav>
            <ul class="pagination">
                <c:if test="${pagina > 0}">
                    <li class="page-item">
                        <a class="page-link" href="?pagina=${pagina - 1}">Anterior</a>
                    </li>
                </c:if>
                <li class="page-item disabled">
                    <span class="page-link">Página ${pagina + 1} de ${totalPaginas}</span>
                </li>
                <c:if test="${pagina + 1 < totalPaginas}">
                    <li class="page-item">
                        <a class="page-link" href="?pagina=${pagina + 1}">Siguiente</a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </c:otherwise>
</c:choose>
</div>
<jsp:include page="layout/footer.jsp"/>