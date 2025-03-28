<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Navbar -->
<nav class="navbar navbar-dark bg-dark navbar-expand-lg">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/inicio.jsp">Gestión de Productos</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas"
                data-bs-target="#offcanvasNavbar"
                aria-controls="offcanvasNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="offcanvas offcanvas-end bg-dark text-white" tabindex="-1" id="offcanvasNavbar"
             aria-labelledby="offcanvasNavbarLabel">
            <div class="offcanvas-header">
                <h5 class="offcanvas-title" id="offcanvasNavbarLabel">Menú</h5>
                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="offcanvas"
                        aria-label="Close"></button>
            </div>
            <div class="offcanvas-body">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link active" href="${pageContext.request.contextPath}/inicio.jsp">Inicio</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownProductos" role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            Productos
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/productos">Ver Productos</a></li>
                            <c:if test="${sessionScope.usuario.role.nombre == 'ROLE_ADMINISTRADOR'}">
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/productos/form">Agregar Productos</a></li>
                            </c:if>
                        </ul>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownPedidos" role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            Pedidos
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/pedidos">Ver Pedidos</a></li>
                        </ul>
                    </li>
                    <c:if test="${sessionScope.usuario.role.nombre == 'ROLE_ADMINISTRADOR'}">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownCategoria" role="button"
                               data-bs-toggle="dropdown" aria-expanded="false">
                                Categorías
                            </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/categorias">Ver Categorías</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/categorias/form">Agregar Categorías</a></li>
                            </ul>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope.usuario.role.nombre == 'ROLE_ADMINISTRADOR'}">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownAccesos" role="button"
                               data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Accesos
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdownAccesos">
                                <!-- Sublista Usuarios -->
                                <li class="dropdown-submenu">
                                    <a class="dropdown-item dropdown-toggle" href="#" id="navbarDropdownUsuarios"
                                       role="button"
                                       data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        Usuarios
                                    </a>
                                    <ul class="dropdown-menu" aria-labelledby="navbarDropdownUsuarios">
                                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/usuario">Ver
                                            Usuarios</a></li>
                                        <li><a class="dropdown-item" href="agregarUsuario.jsp">Agregar Usuario</a></li>
                                    </ul>
                                </li>
                                <!-- Opción Roles -->
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/roles">Ver
                                    Roles</a>
                                </li>
                            </ul>
                        </li>
                    </c:if>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownCarro" role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            Carro <span class="badge bg-light text-dark ms-1">${carro.items.size()}</span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/carro/ver">Ver Carro</a></li>
                        </ul>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownCuenta" role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            ${not empty sessionScope.usuario.username? sessionScope.usuario.username : "Cuenta"}
                        </a>
                        <ul class="dropdown-menu">
                            <li>
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/${not empty sessionScope.usuario.username? "logout" : "login"}">
                                    ${not empty sessionScope.usuario.username? "Cerrar Sesión" : "Iniciar Sesión"}
                                </a>
                            </li>
                            <c:if test="${not empty sessionScope.usuario.username}">
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/perfil">Perfil</a></li>
                            </c:if>
                            <c:if test="${empty sessionScope.usuario.username}">
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/registrar">Registrar</a></li>
                            </c:if>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</nav>