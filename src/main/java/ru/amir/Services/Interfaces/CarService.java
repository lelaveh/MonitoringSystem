package ru.amir.Services.Interfaces;

import org.springframework.stereotype.Service;
import ru.amir.Entities.Car;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;


public interface CarService {

    public void saveCar(Car car);

    public void deleteCar(int id);

    public List<Car> getCars();

    public Car getCar(int id);

    public Car getCarByLP(String licensePlate);

    public List<Car> getCarsByCheckpoint(int destinationId);

}
