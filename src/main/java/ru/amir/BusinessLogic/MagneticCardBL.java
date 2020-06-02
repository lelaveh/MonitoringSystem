package ru.amir.BusinessLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.amir.Entities.Car;
import ru.amir.Entities.LogEntry;
import ru.amir.Services.Interfaces.LogEntryService;

import java.sql.Date;
import java.sql.Time;

@Component
public class MagneticCardBL implements RegisteringObject {

    @Autowired
    private LogEntryService logEntryService;

    private int checkpointId;

    @Override
    public boolean registerVehicle(Car car) {
        Time time = new Time(System.currentTimeMillis());
        Date date= new Date(System.currentTimeMillis());
        if (car.getMagneticCard() != 0) {
            logEntryService.saveLogEntry(new LogEntry(date, time, checkpointId, "Card reader", car.getId(), car.getMagneticCard()));
            return true;
        }
        return false;
    }

    @Override
    public String getCause() {
        return "car hasn't a magnetic card";
    }

    @Override
    public String getName() {
        return "card reader";
    }

    @Override
    public String isCapableRegistering() {
        return "true";
    }

    @Override
    public void setProperty(Object object) {
        this.checkpointId = (Integer) object;
    }


}
