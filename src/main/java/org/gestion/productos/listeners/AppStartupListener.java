package org.gestion.productos.listeners;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.gestion.productos.models.Carro;

@WebListener
public class AppStartupListener implements ServletContextListener, ServletRequestListener, HttpSessionListener {

    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().log("Iniciando contexto de la aplicación");
        servletContext = sce.getServletContext();
        servletContext.setAttribute("mensaje", "Valor global de la aplicación");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        servletContext.log("Finalizando contexto de la aplicación");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        servletContext.log("Iniciando request");
        sre.getServletRequest().setAttribute("mensaje", "Guardar el mensaje en la request");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        servletContext.log("Finalizando request");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        servletContext.log("Iniciando sesión");
        Carro carro = new Carro();
        HttpSession session = se.getSession();
        session.setAttribute("carro", carro);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        servletContext.log("Finalizando sesión");
    }
}
