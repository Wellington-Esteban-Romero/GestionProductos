</div>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${not empty sessionScope.usuario}">
    <footer class="bg-dark text-center text-white mt-auto">
        <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2)">
            &copy; 2025 Copyright:
            <a class="text-white" href="">wellington9@live.com</a>
        </div>
    </footer>
</c:if>
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>
