<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${not empty sessionScope.usuario}">
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/inicio.jsp">Gestión de Productos</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent"
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
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/productos">Ver
                                Productos</a>
                            <c:if test="${sessionScope.usuario.role.nombre == 'ROLE_ADMINISTRADOR'}">
                                <a class="dropdown-item"
                                   href="<c:out value="${pageContext.request.contextPath}"/>/productos/form">Agregar
                                    Productos</a>
                            </c:if>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownPedidos" role="button"
                           data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Pedidos
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownPedidos">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/pedidos">Ver Pedidos</a>
                        </div>
                    </li>
                    <c:if test="${sessionScope.usuario.role.nombre == 'ROLE_ADMINISTRADOR'}">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownCategoria" role="button"
                               data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Categorias
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdownCategoria">
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/categorias">Ver
                                    Categorías</a>
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/categorias/form">Agregar
                                    Categorías</a>
                            </div>
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
                           data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Carro<span class="badge bg-light text-dark ms-1">${carro.items.size()}</span>
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownCarro">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/carro/ver">Ver carro</a>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownCuenta" role="button"
                           data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                ${not empty sessionScope.usuario.username? sessionScope.usuario.username : "Cuenta"}
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownCuenta">
                            <a class="dropdown-item"
                               href="${pageContext.request.contextPath}/${not empty sessionScope.usuario.username? "logout" : "login"}">
                                    ${not empty sessionScope.usuario.username? "Cerrar Sesión" : "Iniciar Sesión"}</a>
                            <c:if test="${not empty sessionScope.usuario.username}">
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/perfil">Perfil</a>
                            </c:if>
                            <c:if test="${empty sessionScope.usuario.username}">
                                <a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/registrar">Registrar</a>
                            </c:if>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</c:if>