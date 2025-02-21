package org.gestion.productos.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.gestion.productos.exceptions.ServiceJdbcException;
import org.gestion.productos.utils.ConexionBD;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter("/*")
public class ConexionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
            IOException, ServletException {

        try (Connection conn = ConexionBD.getConnection()) {

            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }

            try {
                servletRequest.setAttribute("conn", conn);
                filterChain.doFilter(servletRequest, servletResponse);
                conn.commit();
            } catch (SQLException | ServiceJdbcException e) {
                conn.rollback();
                ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
