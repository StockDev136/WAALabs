package com.waa.waaproject.Aspect;

import com.waa.waaproject.domain.Logger;
import com.waa.waaproject.repository.ILoggerRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Aspect
@Component
public class LoggerAspect {
    public final ILoggerRepository loggerRepository;
    @Autowired
    public LoggerAspect(ILoggerRepository loggerRepository) {
        this.loggerRepository = loggerRepository;
    }
    @Pointcut("execution(* com.waa.waaproject.controller.*.*(..))")
    public void controllerLayer(){}
    @After("controllerLayer()")
    public void logOperations(JoinPoint joinPoint){
        String user= "admin";
        String operation = joinPoint.getSignature().getName();
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        Logger logger = new Logger(date,time,user,operation);

        loggerRepository.save(logger);

    }
}
