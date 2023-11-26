package com.example.jpa_project.configuration;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingConfiguration {

    @Pointcut("execution(public * com.example.jpa_project.controller.*.*(..))")
    public void controllerLog() {

    }

    @Pointcut("execution(public * com.example.jpa_project.service.serviceImpl.*.*(..))")
    public void serviceLog() {

    }

    @Before("controllerLog()")
    public void doBeforeControllerLog(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        log.info("\n NEW REQUEST: \n IP: {} \n URL: {} \n HTTP_METHOD: {} \n CONTROLLER_METHOD: {}.{}",
                request.getRemoteAddr(),
                request.getRequestURL().toString(),
                request.getMethod(),
                joinPoint.getSignature().getDeclaringType(), joinPoint.getSignature().getName());
    }

    @Before("serviceLog()")
    public void deBeforeServiceLog(JoinPoint joinPoint) {
        log.info("\n RUN SERVICE: \n SERVICE_METHOD: {}.{}",
                joinPoint.getSignature().getDeclaringType(), joinPoint.getSignature().getName());
    }

    @AfterReturning(returning = "returnObject", pointcut = "controllerLog()")
    public void doAfterReturnController(Object returnObject) {
        if (log.isInfoEnabled()) {
            log.info("RETURN VALUE: {}\n END REQUEST!", returnObject);
        }
    }

    @AfterThrowing(throwing = "exception", pointcut = "controllerLog()")
    public void throwException(JoinPoint joinPoint, Exception exception) {
        log.error("Request throw an exception. Cause - {}.{}",
                Arrays.toString(joinPoint.getArgs()), exception.getMessage());
    }
}











