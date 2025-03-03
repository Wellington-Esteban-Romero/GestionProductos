package org.gestion.productos.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.gestion.productos.models.Categoria;
import org.gestion.productos.services.CategoriaService;
import org.gestion.productos.utils.Utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/categorias/form")
public class CategoriaFormServlet extends HttpServlet {

    @Inject
    private CategoriaService categoriaService;

    @Inject
    private Utils utils;

    String nombreAnterior;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long id = utils.parseLong(req.getParameter("id"));

        Categoria categoria = new Categoria();
        if (id > 0) {
            Optional<Categoria> categoriaOptional = categoriaService.porIdCategoria(id);
            if (categoriaOptional.isPresent()) {
                categoria = categoriaOptional.get();
            }
        }

        nombreAnterior = categoria.getNombre();

        req.setAttribute("title", req.getAttribute("title") + " - Formulario categorías");
        req.setAttribute("categoria", categoria);
        getServletContext().getRequestDispatcher("/form_categoria.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long id = utils.parseLong(req.getParameter("id"));
        String nombre = req.getParameter("nombre");
        String descripcion = req.getParameter("descripcion");

        Categoria categoria = new Categoria();
        categoria.setNombre(nombre);
        categoria.setDescripcion(descripcion);

        Map<String, String> errores = validar(categoria);
        if (errores.isEmpty()) {
            if (id > 0) {
                categoria.setId(id);
            }
            categoriaService.guardar(categoria);
            resp.sendRedirect(req.getContextPath() + "/categorias");
        } else {
            req.setAttribute("errores", errores);
            req.setAttribute("categoria", categoria);
            req.setAttribute("title", req.getAttribute("title") + " - Formulario categoria");
            getServletContext().getRequestDispatcher("/form_categoria.jsp").forward(req, resp);
        }
    }

    private Map<String, String> validar(Categoria categoria) {
        Map<String, String> errores = new HashMap<>();
        if (categoria.getNombre() == null || categoria.getNombre().isEmpty()) {
            errores.put("nombre", "El nombre es obligatorio!");
        } else if (!categoria.getNombre().equals(nombreAnterior) && categoriaService.existe(categoria.getNombre())) {
            errores.put("nombre", "El nombre ya existe!");
        } else if (categoria.getNombre().length() > 45) {
            errores.put("nombre", "El nombre debe tener un tamaño menor de 45 caracteres!");
        }
        return errores;
    }
}
