package org.gestion.productos.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.gestion.productos.exceptions.ServiceJdbcException;

import java.io.IOException;

@WebFilter("/*")
public class ConexionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (ServiceJdbcException e) {
            ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            e.printStackTrace();
        }
    }
}
