package com.waa.waaproject.Aspect;

import com.waa.waaproject.domain.ExceptionHandler;
import com.waa.waaproject.repository.IExceptionRespository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Aspect
@Component
public class ExceptionAspect {
    public final IExceptionRespository exceptionRepository;
    @Autowired
    public ExceptionAspect(IExceptionRespository exceptionRepository) {
        this.exceptionRepository = exceptionRepository;
    }

    @Pointcut("execution(* com.waa.waaproject.controller.*.*(..))")
    public void controllerLayer(){}

    @AfterThrowing(pointcut = "controllerLayer()", throwing = "ex")
    public void logExceptions(JoinPoint joinPoint,Exception ex){
        String user= "admin";
        String operation = joinPoint.getSignature().getName();
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        ExceptionHandler exceptionHandler = new ExceptionHandler(date,time,user,operation,ex.getMessage());
        exceptionRepository.save(exceptionHandler);
    }
}
