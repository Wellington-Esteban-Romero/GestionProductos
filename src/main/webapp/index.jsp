<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<jsp:include page="layout/header.jsp"/>
<h3 class="text-center">${title}</h3>
<c:if test="${not empty sessionScope.mensajeBienvenida}">
    <div id="alerta-bienvenida" class="alert alert-info">Hola <c:out value="${sessionScope.usuario.username}"/>, Has
        iniciado sesión correctamente
    </div>
    <script>
        setTimeout(function () {
            var alerta = document.getElementById('alerta-bienvenida');
            if (alerta) {
                alerta.style.display = 'none';
            }
        }, 3000);
    </script>
    <c:remove var="mensajeBienvenida" scope="session"/>
</c:if>
<!-- Sección de estadísticas -->
<h2 class="mt-4">Estadísticas 📊</h2>
<p><strong>Total de productos registrados:</strong> ${totalProductos}
</p>

<!-- Contenedor para los gráficos -->
<div class="row">
    <div class="col-md-6">
        <h3 class="mt-4">Productos Agregados por Mes</h3>
        <canvas id="productosAgregadosChart"></canvas>
    </div>
    <div class="col-md-6">
        <h3 class="mt-4">Productos Vendidos por Mes</h3>
        <canvas id="productosVendidosChart"></canvas>
    </div>
</div>
<!-- Incluir Chart.js -->
<script src="${pageContext.request.contextPath}/js/chart.js"></script>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        // Datos de productos agregados por mes
        var meses = [];
        var productosAgregados = [];

        <c:forEach var="registro" items="${productosPorMes}">
        meses.push("${registro.mes}");
        productosAgregados.push(${registro.cantidad});
        </c:forEach>;

        var ctx1 = document.getElementById('productosAgregadosChart').getContext('2d');
        new Chart(ctx1, {
            type: 'line',
            data: {
                labels: meses,
                datasets: [{
                    label: 'Productos Agregados',
                    data: productosAgregados,
                    borderColor: '#36A2EB',
                    backgroundColor: 'rgba(54, 162, 235, 0.2)',
                    borderWidth: 2
                }]
            }
        });

// Datos de productos vendidos por mes
        var productosVendidos = [];

        <%--    <c:forEach var="venta" items="${productosVendidosPorMes}">--%>
        productosVendidos.push(22);
        <%--    </c:forEach>;--%>

        var ctx2 = document.getElementById('productosVendidosChart').getContext('2d');
        new Chart(ctx2, {
            type: 'bar',
            data: {
                labels: meses,
                datasets: [{
                    label: 'Productos Vendidos',
                    data: productosVendidos,
                    backgroundColor: '#FF6384',
                    borderWidth: 2
                }]
            }
        });
    });
</script>

<jsp:include page="layout/footer.jsp"/>
