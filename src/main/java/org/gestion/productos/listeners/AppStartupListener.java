package org.gestion.productos.listeners;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class AppStartupListener implements ServletContextListener, ServletRequestListener, HttpSessionListener {

    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().log("Iniciando contexto de la aplicación");
        servletContext = sce.getServletContext();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        servletContext.log("Finalizando contexto de la aplicación");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        servletContext.log("Iniciando request");
        sre.getServletRequest().setAttribute("mensaje", "Guardar el mensaje en la request");
        sre.getServletRequest().setAttribute("title", "Gestión Productos");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        servletContext.log("Finalizando request");
    }

}
