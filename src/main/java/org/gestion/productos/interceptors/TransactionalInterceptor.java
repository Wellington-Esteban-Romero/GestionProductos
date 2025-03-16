package org.gestion.productos.interceptors;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import org.gestion.productos.configs.MysqlConn;

import java.sql.Connection;
import java.util.logging.Logger;

@Transactional
@Interceptor
public class TransactionalInterceptor {

    @Inject
    @MysqlConn
    private Connection conn;

    @Inject
    private Logger log;

    @AroundInvoke
    public Object interceptar(InvocationContext invocation) throws Throwable {
        if (conn.getAutoCommit()) {
            conn.setAutoCommit(false);
        }

        try {
            log.info("***** Accediendo al método " + invocation.getMethod().getName() + " de la clase "
                    + invocation.getMethod().getDeclaringClass());
            Object resultado = invocation.proceed();
            conn.commit();
            log.info("***** Commit y terminado " + invocation.getMethod().getName() + " de la clase "
                    + invocation.getMethod().getDeclaringClass());
            return resultado;
        } catch (Throwable t) {
            conn.rollback();
            throw t;
        }
    }
}
