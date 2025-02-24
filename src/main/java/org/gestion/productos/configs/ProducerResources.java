package org.gestion.productos.configs;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@ApplicationScoped
public class ProducerResources {

    @Resource(name = "jdbc/gestion_productos.DB")
    private DataSource ds;

    @Produces
    @RequestScoped
    @MysqlConn
    private Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public void close (@Disposes @MysqlConn Connection conn) throws SQLException {
        conn.close();
        System.out.println("Connection closed");
    }
}
