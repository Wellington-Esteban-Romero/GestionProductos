package org.gestion.productos.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class InicioFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Verifica si la solicitud es para la página de inicio
        if (httpRequest.getRequestURI().equals(httpRequest.getContextPath() + "/")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/inicio.jsp");
            dispatcher.forward(request, response);
            return;
        }
        chain.doFilter(request, response);
    }
}