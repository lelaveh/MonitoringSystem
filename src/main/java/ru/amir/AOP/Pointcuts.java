package ru.amir.AOP;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("execution(* ru.amir.BusinessLogic.*.registerVehicle(..))")
    public void forRegisterVehicle() {}

    @Pointcut("execution(* ru.amir.BusinessLogic.CheckpointBL.notifyRegisteringObjects(..))")
    public void forNotifyRegisteringObjects() {}
}
