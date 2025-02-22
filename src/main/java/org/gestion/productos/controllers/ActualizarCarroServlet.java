package org.gestion.productos.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.gestion.productos.models.Carro;
import org.gestion.productos.models.ItemCarro;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Optional;

@WebServlet("/carro/actualizar")
public class ActualizarCarroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        if (session.getAttribute("carro") != null) {

            Carro carro = (Carro) session.getAttribute("carro");

            Enumeration<String> paramNames = req.getParameterNames();

            while (paramNames.hasMoreElements()) {
                String paramName = paramNames.nextElement();
                if (paramName.startsWith("cantidad_")) {
                    Long idProducto = Long.parseLong(paramName.substring(9));
                    Integer cantidad = Integer.valueOf(req.getParameter(paramName));
                    Optional<ItemCarro> optionalItem = carro.getItems().stream()
                            .filter(productoItem -> productoItem.getProducto().getId().equals(idProducto))
                            .findAny();
                    optionalItem.ifPresent(itemCarro -> carro.actualizarItem(itemCarro, cantidad));
                }
            }

            String[] idsProductos = req.getParameterValues("eliminarProductoCheckbox");

            if (idsProductos != null) {
                for (String idsProducto : idsProductos) {
                    carro.eliminarItem(Long.parseLong(idsProducto));
                }
            }
        }

        resp.sendRedirect(req.getContextPath() + "/carro/ver");
    }
}
