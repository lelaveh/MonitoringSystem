package ru.amir.BusinessLogic;

import ru.amir.Entities.Car;

public interface RegisteringObject {

    boolean registerVehicle(Car car);

    String getCause();

    String getName();

    String isCapableRegistering();

    void setProperty(Object object);
}
