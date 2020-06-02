package ru.amir.BusinessLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import ru.amir.Entities.Car;
import ru.amir.Entities.Checkpoint;
import ru.amir.Services.Interfaces.CameraService;
import ru.amir.Services.Interfaces.CarService;
import ru.amir.Services.Interfaces.CheckpointService;

import javax.annotation.PostConstruct;
import javax.persistence.Column;
import java.util.List;

@Component
@Scope(value="prototype")
public class CarBL implements Runnable {

    @Autowired
    private CarService carService;

    @Autowired
    private CheckpointService checkpointService;

    @Autowired
    private CheckpointBL checkpointBL;

    private Car car;

    private List<Checkpoint> checkpoints;

    private Checkpoint destinationCheckpoint;

    private Direction direction;

    public CarBL() {
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
        destinationCheckpoint = checkpointService.getCheckpoint(car.getDestinationId());
        if (car.getCurrentLocation() < destinationCheckpoint.getLocation())
            direction = Direction.FORWARD;
        else
            direction = Direction.BACKWARD;
    }

    public List<Checkpoint> getCheckpointsLocations() {
        return checkpoints;
    }

    public void setCheckpointsLocations(List<Checkpoint> checkpointsLocations) {
        this.checkpoints = checkpointsLocations;
    }

    public Checkpoint getDestinationDistance() {
        return destinationCheckpoint;
    }

    public void setDestinationDistance(Checkpoint checkpoint) {
        this.destinationCheckpoint = checkpoint;
    }

    @Override
    public void run() {
        int step;
        checkpoints = checkpointService.getCheckpoints();
        if (car.getCurrentLocation() == destinationCheckpoint.getLocation())
            System.out.println(car + " has already finished its route");
        else {
                if (direction == Direction.FORWARD) {
                   step = 100;
                 } else step = -100;
                for (int i = car.getCurrentLocation(); i != destinationCheckpoint.getLocation(); i += step) {
                    for (Checkpoint checkpoint : checkpoints) {
                       if (checkpoint.getLocation() == i) {
                           checkpointBL.setCheckpoint(checkpoint);
                           checkpointBL.notifyRegisteringObjects(direction, car);
                           break;
                       }
                    }
                    try {
                        System.out.println("..." + i + "...");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            checkpointBL.setCheckpoint(checkpointService.getCheckpoint(car.getDestinationId()));
            checkpointBL.notifyRegisteringObjects(direction, car);
            car.setCurrentLocation(getDestinationDistance().getLocation());
            carService.saveCar(car);
        }
    }
}

enum  Direction {
    FORWARD,
    BACKWARD,
    FINISHED
}
