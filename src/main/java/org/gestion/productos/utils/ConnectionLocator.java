//package org.gestion.productos.utils;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.SQLException;
//
//public class ConnectionLocator {
//
//    public static Connection getConnection() throws SQLException, NamingException {
//        Context initContext = null;
//        DataSource ds = null;
//        try {
//            initContext = new InitialContext();
//            Context envContext = (Context) initContext.lookup("java:/comp/env");
//            ds = (DataSource) envContext.lookup("jdbc/gestion_productos.DB");
//            return ds.getConnection();
//        } catch (NamingException e) {
//            throw new NamingException();
//        }
//    }
//}
