package ru.amir.BusinessLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.amir.Entities.Car;
import ru.amir.Entities.LogEntry;
import ru.amir.Services.Interfaces.LogEntryService;

import java.sql.Date;
import java.sql.Time;


@Component
public class ScalesBL implements RegisteringObject{

    @Autowired
    private LogEntryService logEntryService;

    private int checkpointId;

    public ScalesBL() {
    }

    public int getCheckpointId() {
        return checkpointId;
    }

    public void setCheckpointId(int checkpointId) {
        this.checkpointId = checkpointId;
    }

    @Override
    public String isCapableRegistering() {
        return "true";
    }

    @Override
    public void setProperty(Object object) {
        this.checkpointId = (Integer) object;
    }

    @Override
    public synchronized boolean registerVehicle(Car car) {
        Time time = new Time(System.currentTimeMillis());
        Date date= new Date(System.currentTimeMillis());
        WeightType carWeightType;
        if (car.getWeight() < 3500)
            carWeightType = WeightType.PASSENGER;
        else
            carWeightType = WeightType.TRUCK;
        logEntryService.saveLogEntry(new LogEntry(date, time, checkpointId, "Scales", car.getId(), car.getWeight(), carWeightType.name()));
        return true;
    }

    @Override
    public String getCause() {
        return null;
    }

    @Override
    public String getName() {
        return "scales";
    }
}

enum WeightType {
    PASSENGER,
    TRUCK
}
