<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/inicio.jsp">Gestión de Productos</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active"
                       href="${pageContext.request.contextPath}/inicio.jsp">Inicio</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownProductos" role="button"
                       data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Productos
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownProductos">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/productos">Ver Productos</a>
                        <a class="dropdown-item" href="agregarProducto.jsp">Agregar Productos</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownCategoria" role="button"
                       data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Categorias
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownCategoria">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/categorias">Ver Categorías</a>
                        <a class="dropdown-item" href="agregarCategoria.jsp">Agregar Categorías</a>
                    </div>
                </li>
              <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownUsuario" role="button"
                   data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                  Usuarios
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownUsuario">
                  <a class="dropdown-item" href="${pageContext.request.contextPath}/usuario">Ver Usuarios</a>
                  <a class="dropdown-item" href="agregarUsuario.jsp">Agregar Usuario</a>
                </div>
              </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownCarro" role="button"
                       data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Carro[${carro.items.size()}]
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownCarro">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/carro/ver">Ver carro</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownCuenta" role="button"
                       data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        ${not empty sessionScope.username? sessionScope.username : "Cuenta"}
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownCuenta">
                        <a class="dropdown-item"
                           href="${pageContext.request.contextPath}/${not empty sessionScope.username? "logout" : "login"}">
                          ${not empty sessionScope.username? "Cerrar Sesión" : "Iniciar Sesión"}</a>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</nav>
