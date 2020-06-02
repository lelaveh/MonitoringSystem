package ru.amir.BusinessLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.amir.Entities.Car;
import ru.amir.Entities.Checkpoint;
import ru.amir.Services.Interfaces.CameraService;
import ru.amir.Services.Interfaces.CheckpointService;

import java.util.ArrayList;
import java.util.List;

@Component
public class CheckpointBL {

    @Autowired
    private RegisteringObject cameraBL;

    @Autowired
    private RegisteringObject scalesBL;

    @Autowired
    private RegisteringObject magneticCardBL;

    private Checkpoint checkpoint;


    public CheckpointBL() {
    }

    public Checkpoint getCheckpoint() {
        return checkpoint;
    }

    public void setCheckpoint(Checkpoint checkpoint) {
        this.checkpoint = checkpoint;
    }

    public synchronized void notifyRegisteringObjects(Direction direction, Car car) {
        // setting BL dependencies
        if (direction == Direction.FORWARD) {
            if (checkpoint.getEntryCamera() != null)
                cameraBL.setProperty(checkpoint.getEntryCamera());
        } else
            if (checkpoint.getExitCamera() != null)

                cameraBL.setProperty(checkpoint.getExitCamera());
        scalesBL.setProperty(checkpoint.getId());
        magneticCardBL.setProperty(checkpoint.getId());


        List<RegisteringObject> registeringObjects = new ArrayList<>();
        registeringObjects.add(cameraBL);
        registeringObjects.add(magneticCardBL);
        registeringObjects.add(scalesBL);

        // registering car
        for (RegisteringObject registeringObject : registeringObjects)
            if (registeringObject.isCapableRegistering().equals("true"))
                registeringObject.registerVehicle(car);
            else
                System.out.println(registeringObject.isCapableRegistering());

        cameraBL.setProperty(null);
    }
}
