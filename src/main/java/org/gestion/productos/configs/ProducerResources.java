package org.gestion.productos.configs;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

@ApplicationScoped
public class ProducerResources {

    @Resource(name = "jdbc/gestion_productos.DB")
    private DataSource ds;

    @Inject
    private Logger log;

    @Produces
    @RequestScoped
    @MysqlConn
    private Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public void close (@Disposes @MysqlConn Connection conn) throws SQLException {
        conn.close();
        log.info("Connection closed");
    }

    @Produces
    @Dependent
    private Logger beanLogger(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }
}
