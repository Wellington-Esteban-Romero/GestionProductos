<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<jsp:include page="layout/header.jsp"/>

<div class="container mt-5">
    <h3 class="text-center text-primary mb-4">${title}</h3>

    <c:if test="${username.isPresent()}">
        <div class="bg-light p-4 rounded shadow-sm">
            <form action="${pageContext.request.contextPath}/productos/buscar" method="post">
                <div class="row g-3">
                    <div class="col-md-3">
                        <label for="buscarNombre" class="form-label fw-bold">🔎 Producto</label>
                        <input type="text" class="form-control border-primary" name="buscarNombre" id="buscarNombre"
                               placeholder="Nombre del producto">
                    </div>
                    <div class="col-md-3">
                        <label for="tipo" class="form-label fw-bold">📂 Categoría</label>
                        <input type="text" class="form-control border-primary" name="tipo" id="tipo"
                               placeholder="Nombre categoría">
                    </div>
                    <div class="col-md-3">
                        <label for="precioMin" class="form-label fw-bold">💰 Precio Mínimo</label>
                        <input type="number" class="form-control border-success" id="precioMin" name="precioMin"
                               placeholder="Mínimo (€)" min="0">
                    </div>
                    <div class="col-md-3">
                        <label for="precioMax" class="form-label fw-bold">💰 Precio Máximo</label>
                        <input type="number" class="form-control border-danger" id="precioMax" name="precioMax"
                               placeholder="Máximo (€)">
                    </div>
                    <div class="col-md-3">
                        <label for="fecha_inicio" class="form-label fw-bold">📅 Fecha Inicio</label>
                        <input type="date" class="form-control border-info" name="fecha_inicio" id="fecha_inicio">
                    </div>
                    <div class="col-md-3">
                        <label for="fecha_fin" class="form-label fw-bold">📅 Fecha Fin</label>
                        <input type="date" class="form-control border-info" name="fecha_fin" id="fecha_fin">
                    </div>
                    <div class="col-md-3 d-flex align-items-end">
                        <input class="btn btn-success w-100 fw-bold" type="submit" value="🔍 Buscar">
                    </div>
                </div>
            </form>
        </div>
    </c:if>

    <c:choose>
        <c:when test="${empty productos}">
            <div class="alert alert-secondary text-center mt-4">⚠️ No existen productos disponibles.</div>
        </c:when>
        <c:otherwise>
            <div class="table-responsive mt-4">
                <table class="table table-hover table-striped shadow-sm">
                    <thead class="table-dark text-center">
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Categoría</th>
                        <th>Fecha Registro</th>
                        <c:if test="${username.present}">
                            <th>Precio (€)</th>
                            <th></th>
                            <c:if test="${sessionScope.usuario.role.nombre == 'ROLE_ADMINISTRADOR'}">
                                <th></th>
                                <th></th>
                            </c:if>
                        </c:if>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${productos}" var="producto">
                        <tr class="align-middle text-center">
                            <td>
                                <a class="text-decoration-none text-dark fw-bold"
                                   href="${pageContext.request.contextPath}/productos/detalles?id=<c:out value="${producto.id}"/>">
                                    <c:out value="${producto.id}"/>
                                </a>
                            </td>
                            <td><c:out value="${producto.nombre}"/></td>
                            <td>
                                <span class="badge bg-primary">
                                    <c:out value="${producto.categoria.nombre}"/>
                                </span>
                            </td>
                            <td>
                                <span class="badge bg-info">
                                    <c:out value="${producto.fechaRegistro.format(DateTimeFormatter.ofPattern('dd-MM-yyyy'))}"/>
                                </span>
                            </td>
                            <c:if test="${username.present}">
                                <td class="fw-bold text-success"><c:out value="${producto.precio}"/> €</td>
                                <td>
                                    <a class="btn btn-sm btn-primary w-100"
                                       href="<c:out value="${pageContext.request.contextPath}"/>/carro/agregar?id=<c:out value="${producto.id}"/>">
                                        🛒 Agregar
                                    </a>
                                </td>
                                <c:if test="${sessionScope.usuario.role.nombre == 'ROLE_ADMINISTRADOR'}">
                                    <td>
                                        <a class="btn btn-sm btn-warning w-100 text-dark fw-bold"
                                           href="<c:out value="${pageContext.request.contextPath}"/>/productos/form?id=<c:out value="${producto.id}"/>">
                                            ✏️ Editar
                                        </a>
                                    </td>
                                    <td>
                                        <a class="btn btn-sm btn-danger w-100 fw-bold"
                                           onclick="return confirm('⚠️ ¿Está seguro que quiere eliminar este producto?');"
                                           href="<c:out value="${pageContext.request.contextPath}"/>/productos/eliminar?id=<c:out value="${producto.id}"/>">
                                            🗑️ Eliminar
                                        </a>
                                    </td>
                                </c:if>
                            </c:if>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <!-- Paginación centrada -->
            <nav class="d-flex justify-content-center mt-4">
                <ul class="pagination">
                    <c:if test="${pagina > 0}">
                        <li class="page-item">
                            <a class="page-link text-primary" href="?pagina=${pagina - 1}">⬅️ Anterior</a>
                        </li>
                    </c:if>
                    <li class="page-item disabled">
                        <span class="page-link">📄 Página ${pagina + 1} de ${totalPaginas}</span>
                    </li>
                    <c:if test="${pagina + 1 < totalPaginas}">
                        <li class="page-item">
                            <a class="page-link text-primary" href="?pagina=${pagina + 1}">Siguiente ➡️</a>
                        </li>
                    </c:if>
                </ul>
            </nav>
        </c:otherwise>
    </c:choose>
</div>

<jsp:include page="layout/footer.jsp"/>
