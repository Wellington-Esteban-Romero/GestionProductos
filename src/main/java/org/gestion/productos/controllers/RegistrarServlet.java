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
import org.gestion.productos.services.UsuarioService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet({"/registrar", "/registrar.html"})
public class RegistrarServlet extends HttpServlet {

    @Inject
    private UsuarioService usuarioService;

    @Inject
    private LoginService loginService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Optional<Usuario> usuarioOptional = loginService.getUsername(req);

        if (usuarioOptional.isPresent()) {
            getServletContext().getRequestDispatcher("/ya_iniciado.jsp").forward(req, resp);
        } else {
            req.setAttribute("title", req.getAttribute("title") + " - Registrar");
            getServletContext().getRequestDispatcher("/registrar.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombre = req.getParameter("nombre");
        String apellidos = req.getParameter("apellidos");
        String email = req.getParameter("email");
        String telefono = req.getParameter("telefono");
        String direccion = req.getParameter("direccion");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String repetir_password = req.getParameter("repetir_password");
        req.setAttribute("title", req.getAttribute("title") + " - Registrar");

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellidos(apellidos);
        usuario.setEmail(email);
        usuario.setTelefono(telefono);
        usuario.setDireccion(direccion);
        usuario.setUsername(username);
        usuario.setPassword(password);

        Role role = new Role();
        role.setId(1L);
        usuario.setRole(role);

        Map<String, String> errores = validar(usuario, repetir_password);
        if (!errores.isEmpty()) {
            reenviarFormularioConErrores(req, resp, errores, usuario);
        } else {
            if (usuarioService.registrar(usuario)) {
                resp.sendRedirect(req.getContextPath() + "/login.jsp");
            } else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ha ocurrido un error contacte con la empresa");
            }
        }
    }

    private Map<String, String> validar(Usuario usuario, String repetir_password) {
        Map<String, String> errores = new HashMap<>();

        if (usuario.getNombre().isEmpty()) {
            errores.put("nombre", "El nombre es obligatorio.");
        } else if (!usuario.getNombre().matches("^[a-zA-Z]{3,25}$")) {
            errores.put("nombre", "El nombre debe contener entre 3 y 25 letras sin caracteres especiales ni números.");
        }

        if (usuario.getApellidos().isEmpty()) {
            errores.put("apellidos", "Los apellidos es obligatorio.");
        } else if (!usuario.getApellidos().matches("^[a-zA-Z]{3,45}$")) {
            errores.put("apellidos", "Los apellidos deben contener entre 3 y 45 letras sin caracteres especiales ni números.");
        }

        if (usuario.getEmail().isEmpty()) {
            errores.put("email", "El campo correo es obligatorio.");
        } else if (!usuario.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            errores.put("email", "Ingrese un correo electrónico válido.");
        }

        if (usuario.getTelefono().isEmpty()) {
            errores.put("telefono", "El teléfono es obligatorio.");
        } else if (!usuario.getTelefono().matches("^6[0-9]{8}$")) {
            errores.put("telefono", "El teléfono debe tener 9 dígitos y empezar por 6.");
        }

        if (usuario.getDireccion().isEmpty()) {
            errores.put("direccion", "El campo dirección es obligatorio.");
        } else if (!(usuario.getDireccion().length() >= 3 && usuario.getDireccion().length() <= 80)) {
            errores.put("direccion", "La dirección debe tener entre 3 y 80 caracteres.");
        }

        if (usuario.getUsername().isEmpty()) {
            errores.put("usuario", "El campo usuario es obligatorio!");
        } else if (!usuario.getUsername().matches("^[a-zA-Z0-9_]{3,12}$")) {
            errores.put("usuario", "El campo usuario debe ser alfanumérico y deber contener entre 3 y 12 caracteres!");
        }

        if (usuario.getPassword().isEmpty()) {
            errores.put("contrasenia", "La contraseña es obligatoria.");
        } else if (!usuario.getPassword().equals(repetir_password)) {
            errores.put("repetir_password", "Las contraseñas no coinciden.");
        }
        return errores;
    }

    private void reenviarFormularioConErrores(HttpServletRequest req, HttpServletResponse resp,
                                              Map<String, String> errores, Usuario usuario)
            throws ServletException, IOException {
        req.setAttribute("errores", errores);
        req.setAttribute("usuario", usuario);
        getServletContext().getRequestDispatcher("/registrar.jsp").forward(req, resp);
    }
}
