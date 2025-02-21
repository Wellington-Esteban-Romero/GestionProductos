package org.gestion.productos.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static String url = "jdbc:mysql://localhost:3306/gestion_productos?serverTimezone=Europe/Madrid";
    private static String user = "root";
    private static String password = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
