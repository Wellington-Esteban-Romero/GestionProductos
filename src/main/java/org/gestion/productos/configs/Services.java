package org.gestion.productos.configs;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Stereotype;
import jakarta.inject.Named;
import org.gestion.productos.interceptors.Logging;
import org.gestion.productos.interceptors.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Transactional
@Logging
@ApplicationScoped
@Stereotype
@Named
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Services {
}
