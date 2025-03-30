<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="d-flex justify-content-center mt-4">
    <ul class="pagination">
        <c:if test="${pagina > 0}">
            <li class="page-item">
                <a class="page-link text-primary" href="?pagina=${pagina - 1}">⬅️ Anterior</a>
            </li>
        </c:if>
        <li class="page-item disabled">
            <span class="page-link">📄 Página ${pagina + 1} de ${totalPaginas}</span>
        </li>
        <c:if test="${pagina + 1 < totalPaginas}">
            <li class="page-item">
                <a class="page-link text-primary" href="?pagina=${pagina + 1}">Siguiente ➡️</a>
            </li>
        </c:if>
    </ul>
</nav>