package ru.amir.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import ru.amir.BusinessLogic.CheckpointBL;
import ru.amir.BusinessLogic.RegisteringObject;


@Aspect
@Component
public class LoggingAspect {

    @AfterReturning(value = "ru.amir.AOP.Pointcuts.forRegisterVehicle()", returning = "result")
    public void afterReturningRegisterVehicle(JoinPoint joinPoint, boolean result) {
        RegisteringObject registeringObject = (RegisteringObject) joinPoint.getTarget();
        if (result)
            System.out.println("Vehicle was registered successfully by " + registeringObject.getName());
        else
            System.out.println("Vehicle was not registered by " + registeringObject.getName() + " because: " + registeringObject.getCause());
    }

    @Before(value = "ru.amir.AOP.Pointcuts.forNotifyRegisteringObjects()")
    public void beforeNotifyRegisteringObjects(JoinPoint joinPoint) {
        CheckpointBL checkpointBL = (CheckpointBL) joinPoint.getTarget();
        System.out.println("At checkpoint № " + checkpointBL.getCheckpoint().getId());
    }
}
