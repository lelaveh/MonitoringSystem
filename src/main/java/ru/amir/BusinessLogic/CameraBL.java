package ru.amir.BusinessLogic;

import org.aspectj.lang.annotation.AfterReturning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.amir.Entities.Car;
import ru.amir.Entities.LogEntry;
import ru.amir.Entities.SecurityCamera;
import ru.amir.Services.Interfaces.CheckpointService;
import ru.amir.Services.Interfaces.LogEntryService;

import javax.annotation.PostConstruct;
import java.sql.Date;
import java.sql.Time;

@Component
public class CameraBL implements RegisteringObject {

    @Autowired
    private LogEntryService logEntryService;

    @Autowired
    CheckpointService checkpointService;

    private SecurityCamera camera;

    private volatile Boolean isVehiclePresent;


    public CameraBL() {
    }

    public SecurityCamera getCamera() {
        return camera;
    }

    public Boolean getVehiclePresent() {
        return isVehiclePresent;
    }

    public void setVehiclePresent(Boolean vehiclePresent) {
        isVehiclePresent = vehiclePresent;
    }

    @Override
    public void setProperty(Object object) {
        this.camera = (SecurityCamera) object;
    }

    @Override
    public String getCause() {
        return "сamera is not turned on";
    }

    @Override
    public String getName() {
        return "camera № " + camera.getId();
    }

    @Override
    public String isCapableRegistering() {
        return camera != null ? "true" : "No camera at checkpoint";
    }

    @Override
    public synchronized boolean registerVehicle(Car car) {
        Time time = new Time(System.currentTimeMillis());
        Date date= new Date(System.currentTimeMillis());
        Direction direction;
        int currentLocation = car.getCurrentLocation();
        int destinationLocation = checkpointService.getCheckpoint(car.getDestinationId()).getLocation();
        if (currentLocation < destinationLocation)
            direction = Direction.FORWARD;
        else if (currentLocation > destinationLocation)
            direction = Direction.BACKWARD;
        else
            direction = Direction.FINISHED;
        String objectId = "Camera №" + camera.getId();
        if (camera.isPower()) {
            logEntryService.saveLogEntry(new LogEntry(date, time, camera.getCheckpointId(), objectId, car.getId(), direction.name(), car.getRegNum()));
            return true;
        }
        return false;

    }

}
