package org.gestion.productos.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.gestion.productos.models.Role;
import org.gestion.productos.models.Usuario;
import org.gestion.productos.services.LoginService;
import org.gestion.productos.services.RoleService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet({"/roles.html", "/roles"})
public class RoleServlet extends HttpServlet {

    @Inject
    private RoleService roleService;

    @Inject
    private LoginService loginService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Role> roles = roleService.listarRoles();
        Optional<Usuario> usuarioOptional = loginService.getUsername(req);
        req.setAttribute("roles", roles);
        req.setAttribute("username", usuarioOptional);
        req.setAttribute("title", req.getAttribute("title") + " - Lista Roles");
        getServletContext().getRequestDispatcher("/listar_roles.jsp").forward(req, resp);
    }
}
