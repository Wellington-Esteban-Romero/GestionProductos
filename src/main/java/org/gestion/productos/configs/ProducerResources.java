package org.gestion.productos.configs;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ProducerResources {

    @Resource(name = "jdbc/gestion_productos.DB")
    private DataSource ds;

    @Produces
    @RequestScoped
    @MysqlConn
    private Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
