<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Formulario login</title>
    <c:import url="views/links.jsp"/>
</head>
<body>
<c:import url="views/navbar.jsp" />
<div class="container mt-4">
    <h1>Iniciar sesión</h1>
    <form action="<%=request.getContextPath()%>/login" method="post">
        <div class="row mb-3">
            <label for="username" class="col-form-label col-sm-2">Username</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" name="username" id="username">
            </div>
        </div>
        <div class="row mb-3">
            <label for="password" class="col-form-label col-sm-2">Password</label>
            <div class="col-sm-4">
                <input type="password" class="form-control" name="password" id="password">
            </div>
        </div>
        <div class="row mb-3">
            <div>
                <input type="submit" class="btn btn-primary" value="Enviar">
            </div>
        </div>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>