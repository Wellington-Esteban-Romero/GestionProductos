package org.gestion.productos.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.gestion.productos.models.Usuario;
import org.gestion.productos.services.UsuarioService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet({"/registrar", "/registrar.html"})
public class RegistrarServlet extends HttpServlet {

    @Inject
    private UsuarioService usuarioService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/registrar.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");//adaptarlo a registro usuario
        String password = req.getParameter("password");
        String repetir_password = req.getParameter("repetir_password");
        req.setAttribute("title", req.getAttribute("title") + " - Iniciar Sesión");

        Map<String, String> errores = validar(username, repetir_password);
        if (!errores.isEmpty()) {
            reenviarFormularioConErrores(req, resp, errores, username);
        } else {
            Optional<Usuario> usuario = usuarioService.iniciarSesion(username, repetir_password);

            if (usuario.isPresent()) {
                HttpSession httpSession = req.getSession();
                httpSession.setAttribute("username", username);

                resp.sendRedirect(req.getContextPath() + "/");
            } else {
                errores.put("invalidoCredenciales", "Usuario o Contraseña incorrectos");
                reenviarFormularioConErrores(req, resp, errores, username);
            }
        }
    }

    private Map<String, String> validar(String username, String password) {
        Map<String, String> errores = new HashMap<>();

        if (username.isEmpty()) {
            errores.put("usuario", "El usuario es obligatorio!");
        } else if (!username.matches("^[a-zA-Z0-9_]{3,12}$")) {
            errores.put("usuario", "El usuario debe ser alfanumérico y deber contener entre 3 y 12 caracteres!");
        }

        if (password.isEmpty()) {
            errores.put("contrasenia", "La contraseña es obligatorio!");
        }
        return errores;
    }

    private void reenviarFormularioConErrores(HttpServletRequest req, HttpServletResponse resp, Map<String, String> errores, String username) throws ServletException, IOException {
        req.setAttribute("errores", errores);
        req.setAttribute("username", username);
        getServletContext().getRequestDispatcher("/registrar.jsp").forward(req, resp);
    }
}
