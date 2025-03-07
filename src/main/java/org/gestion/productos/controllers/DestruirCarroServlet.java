package org.gestion.productos.controllers;

import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.gestion.productos.models.Carro;

import java.io.IOException;

@WebServlet("/carro/destruir")
public class DestruirCarroServlet extends HttpServlet {

    @Inject
    private Instance<Carro> carroInstance;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!carroInstance.isUnsatisfied()) {
            carroInstance.destroy(carroInstance.get());
        }

        response.sendRedirect("inicio.jsp");
    }
}
