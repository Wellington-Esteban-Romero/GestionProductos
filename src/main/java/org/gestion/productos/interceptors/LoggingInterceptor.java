package org.gestion.productos.interceptors;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

import java.util.logging.Logger;

@Logging
@Interceptor
public class LoggingInterceptor {

    @Inject
    private Logger log;

    @AroundInvoke
    public Object interceptar(InvocationContext invocation) throws Throwable {
        log.info("------ Accediendo antes de el método " + invocation.getMethod().getName() + " de la clase "
                + invocation.getMethod().getDeclaringClass());
        Object result = invocation.proceed();
        log.info("Saliendo del método " + invocation.getMethod().getName());
        return invocation.proceed();
    }
}
