package org.gestion.productos.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@WebServlet("/producto/imagen/*")
public class ProductoImagenServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String imagePath = getServletContext().getRealPath("/imagenes") + req.getPathInfo();
        System.out.println("*************** "+imagePath);
        File file = new File(imagePath);

        if (file.exists()) {
            resp.setContentType(getServletContext().getMimeType(imagePath));
            Files.copy(file.toPath(), resp.getOutputStream());
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
