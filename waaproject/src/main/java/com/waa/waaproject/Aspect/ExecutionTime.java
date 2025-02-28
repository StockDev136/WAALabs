package com.waa.waaproject.Aspect;

import com.waa.waaproject.domain.Logger;
import com.waa.waaproject.repository.ILoggerRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Aspect
@Component
public class ExecutionTime {

    public final ILoggerRepository loggerRepository;
    @Autowired
    public ExecutionTime(ILoggerRepository loggerRepository) {
        this.loggerRepository = loggerRepository;
    }

    @Pointcut("@annotation(com.waa.waaproject.Aspect.Annotation.IExecutionTime)")
    public void executionTime(){}

    @Around("executionTime()")
    public void logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable{
        long startTime = System.currentTimeMillis();
        joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        String user= "admin";
        String operation = "Method execution time: " + (endTime - startTime) + " ms";
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        Logger logger = new Logger(date,time,user,operation);
        //loggerRepository.save(logger);
        System.out.println(operation);
    }
}
