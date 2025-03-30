<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<jsp:include page="layout/header.jsp"/>

<div class="flex-grow-1">
    <h3 class="text-center text-primary">${title}</h3>
    <c:choose>
        <c:when test="${empty pedidos}">
            <div class="alert alert-secondary">No existe ningún pedido</div>
        </c:when>
        <c:otherwise>
            <c:forEach items="${pedidos}" var="pedido">
                <div class="accordion" id="accordionPedido_${pedido.id}">
                    <div class="accordion-item">
                        <h2 class="accordion-header">
                            <button class="accordion-button" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#collapse_${pedido.id}"
                                    aria-expanded="true" aria-controls="collapse_${pedido.id}">
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
                        <div id="collapse_${pedido.id}" class="accordion-collapse collapse show"
                             data-bs-parent="#accordionPedido_${pedido.id}">
                            <div class="accordion-body">
                                <a class="btn btn-sm btn-light">Detalles del pedido</a>
                                <div class="mt-3 mb-3">
                                    <c:out value="${pedido.estado.nombre}"/>
                                </div>
                                <table class="table table-hover table-striped">
                                    <tr>
                                        <th>Nombre</th>
                                        <th>Precio</th>
                                        <th>Unidad/es</th>
                                    </tr>
                                    <c:forEach items="${detalles}" var="detalle">
                                        <c:if test="${pedido.id eq detalle.pedido.id}">
                                            <tr>
                                                <td>
                                                    <c:out value="${detalle.producto.nombre}"/>
                                                </td>
                                                <td><c:out value="${detalle.total}"/>€</td>
                                                <td><c:out value="${detalle.cantidad}"/></td>
                                            </tr>
                                        </c:if>
                                    </c:forEach>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>

            <!-- Paginación -->
            <jsp:include page="layout/paginacion.jsp"/>
        </c:otherwise>
    </c:choose>
</div>

<jsp:include page="layout/footer.jsp"/>