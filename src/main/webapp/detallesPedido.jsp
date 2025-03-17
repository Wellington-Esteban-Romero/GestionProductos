<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<jsp:include page="layout/header.jsp"/>
<%--<div class="row">--%>
<%--  <!-- Columna izquierda: Total a pagar -->--%>
<%--  <div class="col-md-3">--%>
<%--    <div class="card p-3 shadow">--%>
<%--      <h4>Total a pagar:</h4>--%>
<%--      <h2 class="text-success">$<%= String.format("%.2f", total) %></h2>--%>
<%--      <button class="btn btn-primary w-100 mt-3">Finalizar Compra</button>--%>
<%--    </div>--%>
<%--  </div>--%>

<%--  <!-- Columna derecha: Datos del comprador y productos -->--%>
<%--  <div class="col-md-9">--%>
<%--    <div class="card p-4 shadow">--%>
<%--      <h4>Dirección de Envío</h4>--%>
<%--      <p><strong>Nombre:</strong> <%= usuario.getNombre() %></p>--%>
<%--      <p><strong>Dirección:</strong> <%= usuario.getDireccion() %></p>--%>
<%--      <p><strong>Teléfono:</strong> <%= usuario.getTelefono() %></p>--%>
<%--    </div>--%>

<%--    <div class="card p-4 mt-3 shadow">--%>
<%--      <h4>Productos en el Carrito</h4>--%>
<%--      <table class="table table-striped">--%>
<%--        <thead>--%>
<%--        <tr>--%>
<%--          <th>Producto</th>--%>
<%--          <th>Cantidad</th>--%>
<%--          <th>Precio Unitario</th>--%>
<%--          <th>Subtotal</th>--%>
<%--        </tr>--%>
<%--        </thead>--%>
<%--        <tbody>--%>
<%--        <% for (Producto p : carrito) { %>--%>
<%--        <tr>--%>
<%--          <td><%= p.getNombre() %></td>--%>
<%--          <td><%= p.getCantidad() %></td>--%>
<%--          <td>$<%= String.format("%.2f", p.getPrecio()) %></td>--%>
<%--          <td>$<%= String.format("%.2f", p.getCantidad() * p.getPrecio()) %></td>--%>
<%--        </tr>--%>
<%--        <% } %>--%>
<%--        </tbody>--%>
<%--      </table>--%>
<%--    </div>--%>
<%--  </div>--%>
<%--</div>--%>

<jsp:include page="layout/footer.jsp"/>