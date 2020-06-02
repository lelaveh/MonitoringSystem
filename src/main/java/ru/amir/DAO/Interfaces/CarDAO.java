package ru.amir.DAO.Interfaces;

import ru.amir.Entities.Car;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface CarDAO {

    public List<Car> getCars();

    public Car getCar(int id);

    public void deleteCar(int id);

    public void saveCar(Car car);

    public Car getCarByLP(String licensePlate);

    public List<Car> getCarsByCheckpoint(int destinationId);
}
