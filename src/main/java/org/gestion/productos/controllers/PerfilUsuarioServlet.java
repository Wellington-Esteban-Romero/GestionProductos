package org.gestion.productos.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.gestion.productos.models.Usuario;
import org.gestion.productos.services.LoginService;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/perfil")
public class PerfilUsuarioServlet extends HttpServlet {

    @Inject
    private LoginService loginService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<Usuario> usuarioOptional = loginService.getUsername(req);
        if (usuarioOptional.isPresent()) {
            req.setAttribute("username", usuarioOptional.get());
            getServletContext().getRequestDispatcher("/form_perfil.jsp").forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

}

