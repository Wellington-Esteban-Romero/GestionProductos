<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.gestion.productos.models.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% Carro carro = (Carro) session.getAttribute("carro");%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Carro de compras</title>
</head>
<body>
<h1>Carro de compras</h1>
<c:choose>
    <c:when test="${carro == null || sessionScope.carro.items.isEmpty()}">
        <p>No hay productos en el carro compra</p>
    </c:when>
    <c:otherwise>
        <form action="${pageContext.request.contextPath}/carro/actualizar" method="post" name="formCarro">
            <table>
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
                    <td colspan="5" style="text-align: right">Total</td>
                    <td>${carro.total}
                    </td>
                </tr>
            </table>
            <a href="javascript:document.formCarro.submit();">Actualizar</a>
        </form>
    </c:otherwise>
</c:choose>
<p><a href="${pageContext.request.contextPath}/productos">Seguir comprando</a></p>
<p><a href="${pageContext.request.contextPath}/index.html">Volver</a></p>
</body>
</html>