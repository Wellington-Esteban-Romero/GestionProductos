<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="layout/header.jsp"/>
<h3 class="text-center">${title}</h3>
<c:choose>
    <c:when test="${carro == null || sessionScope.carro.items.isEmpty()}">
        <div class="alert alert-warning">No hay productos en el carro compra</div>
    </c:when>
    <c:otherwise>
        <form action="${pageContext.request.contextPath}/carro/actualizar" method="post" name="formCarro">
            <table class="table table-hover table-striped">
                <tr>
                    <th>Id</th>
                    <th>Nombre</th>
                    <th>Tipo</th>
                    <th>Precio</th>
                    <th>Cantidad</th>
                    <th>Total</th>
                    <th>Borrar</th>
                </tr>
                <c:forEach items="${carro.items}" var="item">
                    <tr>
                        <td>${item.producto.id}
                        </td>
                        <td>${item.producto.nombre}
                        </td>
                        <td>${item.producto.categoria.nombre}
                        </td>
                        <td>${item.producto.precio}
                        </td>
                        <td><input type="text" name="cantidad_${item.producto.id}"
                                   value="${item.cantidad}">
                        </td>
                        <td>${item.importe}
                        </td>
                        <td><input type="checkbox" name="eliminarProductoCheckbox" value="${item.producto.id}">
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="6" style="text-align: right">Total</td>
                    <td>${carro.total}
                    </td>
                </tr>
            </table>
            <a class="btn btn-primary" href="javascript:document.formCarro.submit();">Actualizar</a>
        </form>
    </c:otherwise>
</c:choose>
<div class="mt-3">
    <a class="btn btn-sm btn-secondary" href="${pageContext.request.contextPath}/productos">Volver</a>
    <a class="btn btn-sm btn-success" href="${pageContext.request.contextPath}/productos">Seguir comprando</a>
</div>
<jsp:include page="layout/footer.jsp"/>