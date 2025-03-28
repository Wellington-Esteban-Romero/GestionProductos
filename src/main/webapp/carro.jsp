<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="layout/header.jsp"/>

<div class="container-fluid py-3">
    <h3 class="text-center text-primary">${title}</h3>

    <c:choose>
        <c:when test="${carro.items.isEmpty()}">
            <div class="alert alert-warning text-center">
                <i class="bi bi-cart-x-fill"></i> No hay productos en el carro de compra
            </div>
        </c:when>
        <c:otherwise>
            <form action="${pageContext.request.contextPath}/carro/actualizar" method="post" name="formCarro">
                <div class="table-responsive">
                    <table class="table table-hover table-striped shadow-sm text-center">
                        <thead class="table-dark">
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Tipo</th>
                            <th>Precio</th>
                            <th>Cantidad</th>
                            <th>Total</th>
                            <th>Borrar</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${carro.items}" var="item">
                            <tr class="align-middle">
                                <td>${item.producto.id}</td>
                                <td>${item.producto.nombre}</td>
                                <td>${item.producto.categoria.nombre}</td>
                                <td><span class="fw-bold text-success">${item.producto.precio} €</span></td>
                                <td>
                                    <input type="number" class="form-control text-center" name="cantidad_${item.producto.id}"
                                           value="${item.cantidad}" min="1">
                                </td>
                                <td><span class="fw-bold text-primary">${item.importe} €</span></td>
                                <td>
                                    <input type="checkbox" class="form-check-input" name="eliminarProductoCheckbox" value="${item.producto.id}">
                                </td>
                            </tr>
                        </c:forEach>
                        <tr class="table-info fw-bold">
                            <td colspan="6" class="text-end">Total</td>
                            <td><span class="text-success">${carro.total} €</span></td>
                        </tr>
                        </tbody>
                    </table>
                </div>

                <div class="d-flex justify-content-between mt-3">
                    <a class="btn btn-primary w-50 me-2" href="javascript:document.formCarro.submit();">
                        <i class="bi bi-arrow-repeat"></i> Actualizar
                    </a>
                    <c:if test="${not empty carro.items}">
                        <a class="btn btn-warning w-50" href="${pageContext.request.contextPath}/pedidos/continuar">
                            <i class="bi bi-check-circle"></i> Continuar
                        </a>
                    </c:if>
                </div>
            </form>
        </c:otherwise>
    </c:choose>

    <div class="mt-3 text-center">
        <a class="btn btn-success btn-lg" href="${pageContext.request.contextPath}/productos">
            <i class="bi bi-cart-plus-fill"></i> ${carro.items.isEmpty()? "Comprar" : "Seguir comprando"}
        </a>
    </div>
</div>

<jsp:include page="layout/footer.jsp"/>
