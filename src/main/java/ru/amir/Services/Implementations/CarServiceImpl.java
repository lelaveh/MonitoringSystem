package ru.amir.Services.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.amir.DAO.Interfaces.CarDAO;
import ru.amir.Entities.Car;
import ru.amir.Services.Interfaces.CarService;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarDAO carDAO;

    @Transactional
    @Override
    public List<Car> getCars() {
        return carDAO.getCars();
    }

    @Transactional
    @Override
    public Car getCar(int id) {
        return carDAO.getCar(id);
    }

    @Transactional
    @Override
    public void deleteCar(int id) {
        carDAO.deleteCar(id);
    }

    @Transactional
    @Override
    public void saveCar(Car car) {
        carDAO.saveCar(car);
    }

    @Transactional
    @Override
    public Car getCarByLP(String licensePlate) {
        return carDAO.getCarByLP(licensePlate);
    }

    @Transactional
    @Override
    public List<Car> getCarsByCheckpoint(int destinationId) { return carDAO.getCarsByCheckpoint(destinationId); }
}
