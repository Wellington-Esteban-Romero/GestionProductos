<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="layout/header.jsp" />
<h3 class="text-center">${title}</h3>
    <form action="${pageContext.request.contextPath}/login" method="post">
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
                <input type="submit" class="btn btn-primary" value="Iniciar Sesión">
            </div>
        </div>
    </form>
<jsp:include page="layout/footer.jsp" />