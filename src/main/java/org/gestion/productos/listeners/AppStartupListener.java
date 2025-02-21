//package org.gestion.productos.listeners;
//
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebListener;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//
//@WebListener
//public class AppStartupListener implements ServletContextListener, ServletRequestListener {
//
//    @Override
//    public void requestInitialized(ServletRequestEvent sre) {
//        ServletRequest request = sre.getServletRequest();
//
//        try {
//            ((HttpServletResponse) sre.getServletRequest().getServletContext()
//                    .getRequestDispatcher("/inicio")).forward(request, sre.getServletRequest());
//        } catch (ServletException | IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void requestDestroyed(ServletRequestEvent sre) {
//        ServletRequestListener.super.requestDestroyed(sre);
//    }
//
//}
