package org.gestion.productos.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.gestion.productos.models.Usuario;
import org.gestion.productos.services.LoginService;
import org.gestion.productos.services.UsuarioService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet({"/login", "/login.html"})
public class LoginServlet extends HttpServlet {

    @Inject
    private UsuarioService usuarioService;

    @Inject
    private LoginService loginService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Optional<String> username = loginService.getUsername(req);

        if (username.isPresent()) {
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("   <head>");
                out.println("       <meta charset=\"UTF-8\">");
                out.println("       <title>Hola " + username.get() + "</title>");
                out.println("   </head>");
                out.println("   <body>");
                out.println("       <h1>Hola " + username.get() + " ya has iniciado sesión</h1>");
                out.println("   <p><a href='" + req.getContextPath() + "/'>Volver</a></p>");
                out.println("   <p><a href='" + req.getContextPath() + "/logout'>Cerrar sesión</a></p>");
                out.println("   </body>");
                out.println("</html>");
            }
        } else {
            req.setAttribute("title", req.getAttribute("title") + " - Iniciar Sesión");
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        req.setAttribute("title", req.getAttribute("title") + " - Iniciar Sesión");

        Map<String, String> errores = validar(username, password);
        if (!errores.isEmpty()) {
            req.setAttribute("errores", errores);
            req.setAttribute("username", username);
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        } else {
            Optional<Usuario> usuario = usuarioService.iniciarSesion(username, password);

            if (usuario.isPresent()) {
                HttpSession httpSession = req.getSession();
                httpSession.setAttribute("username", username);

                resp.sendRedirect(req.getContextPath() + "/");
            } else {
                resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid username or password");
            }
        }
    }

    private Map<String, String> validar(String username, String password) {
        Map<String, String> errores = new HashMap<>();

        if (username.isEmpty()) {
            errores.put("nombre", "El nombre es obligatorio!");
        } else if (!username.matches("^[a-zA-Z0-9_]{3,12}$")) {
            errores.put("nombre", "El nombre debe ser alfanumérico y deber contener entre 3 y 12 caracteres!");
        }

        if (password.isEmpty()) {
            errores.put("password", "El password es obligatorio!");
        }
        return errores;
    }
}
