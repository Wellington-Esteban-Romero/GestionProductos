<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<jsp:include page="layout/header.jsp"/>
<div class="flex-grow-1">
    <h3 class="text-center">${title}</h3>
    <c:choose>
        <c:when test="${empty pedidos}">
            <div class="alert alert-secondary">No existe ningún pedido</div>
        </c:when>
        <c:otherwise>
            <c:forEach items="${pedidos}" var="pedido">
                <div class="accordion" id="accordionPedido">
                    <div class="accordion-item">
                        <h2 class="accordion-header">
                            <button class="accordion-button" type="button" data-bs-toggle="collapse"
                                    data-bs-target="<c:out value="#"/><c:out value="collapse_"/>${pedido.id}"
                                    aria-expanded="true" aria-controls="<c:out value="collapse_"/>${pedido.id}">
                                <table>
                                    <tr>
                                        <th>Pedido</th>
                                        <th></th>
                                        <th></th>
                                        <th>Nº pedido</th>
                                    </tr>
                                    <tr>
                                        <td><c:out
                                                value="${pedido.fecha_pedido.format(DateTimeFormatter.ofPattern('dd-MM-yyyy'))}"/></td>
                                        <td></td>
                                        <td></td>
                                        <td style="text-align: right;"><c:out value="${pedido.id}"/></td>
                                    </tr>
                                </table>
                            </button>
                        </h2>
                        <div id="<c:out value="collapse_"/>${pedido.id}" class="accordion-collapse collapse show"
                             data-bs-parent="#accordionPedido">
                            <div class="accordion-body">
                                <a class="btn btn-sm btn-light" >Detalles del pedido</a>
                                <div class="mt-3 mb-3">
                                    <c:out value="${pedido.estado.nombre}"/>
                                </div>
                                <table class="table table-hover table-striped">
                                    <tr>
                                        <th>id</th>
                                        <th>Usuario</th>
                                        <th>Estado</th>
                                        <th>Fecha Pedido</th>
                                        <th>Total</th>
                                        <th></th>
                                        <th></th>
                                    </tr>
                                    <c:forEach items="${pedidos}" var="pedido">
                                        <tr>
                                            <td>
                                                <a style="text-decoration:none;"
                                                   href="${pageContext.request.contextPath}/pedido/detalles?id=<c:out value="${pedido.id}"/>">
                                                    <c:out value="${pedido.id}"/>
                                                </a>
                                            </td>
                                            <td>
                                                <c:out value="${pedido.usuario.nombre}"/>
                                                <c:out value="${pedido.usuario.apellidos}"/>
                                            </td>
                                            <td><c:out value="${pedido.estado.nombre}"/>
                                            </td>
                                            <td><c:out
                                                    value="${pedido.fecha_pedido.format(DateTimeFormatter.ofPattern('dd-MM-yyyy'))}"/>
                                            </td>
                                            <td>
                                                    <c:out value="${pedido.total}"/>
                                                    <c:out value="€"/>
                                            <td><a class="btn btn-sm btn-success"
                                                   href="<c:out value="${pageContext.request.contextPath}"/>/pedidos/form?id=<c:out value="${pedido.id}"/>">Editar</a>
                                            </td>
                                            <td><a class="btn btn-sm btn-danger"
                                                   onclick="return confirm('¿Esta seguro que quiere eliminar?');"
                                                   href="<c:out value="${pageContext.request.contextPath}"/>/pedidos/eliminar?id=<c:out value="${pedido.id}"/>">Eliminar</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
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