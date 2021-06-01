package com.pruebatecnica.heroes.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import org.springframework.stereotype.Component;




@Aspect
@Component
public class TiempoAspect {

    @Pointcut("@annotation(com.pruebatecnica.heroes.anotaciones.Tiempo)")
    private void pointcut() {}
    
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
                 // logger
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
 
                 // Obtener el nombre de la clase de destino
        String clazzName = joinPoint.getTarget().getClass().getName();
 
                 // Obtener el nombre del método de la clase de destino
        String methodName = joinPoint.getSignature().getName();
 
        long start = System.currentTimeMillis();
 
                 // llamar al método de destino
        Object result = joinPoint.proceed();
 
        long time = System.currentTimeMillis() - start;
        logger.info( "El metododel controlador {}: tarda en ejecutarse: {} ms",  methodName, time);
 
        return result;
    }
}
