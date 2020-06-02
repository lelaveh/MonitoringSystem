package ru.amir.DAO.Implementations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.stereotype.Repository;
import ru.amir.DAO.Interfaces.CarDAO;
import ru.amir.Entities.Car;

import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Types;
import java.util.List;

@Repository
public class CarDAOImpl implements CarDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Car> getCars() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Car order by id", Car.class).getResultList();
    }

    @Override
    public Car getCar(int id) {
        return sessionFactory.getCurrentSession().get(Car.class, id);
    }

    @Override
    public void deleteCar(int id) {
        Car car = getCar(id);
        sessionFactory.getCurrentSession().delete(car);
    }

    @Override
    public void saveCar(Car car) {
        sessionFactory.getCurrentSession().saveOrUpdate(car);
    }

    @Override
    public Car getCarByLP(String licensePlate) {
        SqlParameterValue sqlParameterValue = new SqlParameterValue(Types.VARCHAR, licensePlate);
        List<Car> car = sessionFactory.getCurrentSession().createQuery("from Car where regNum = '" + sqlParameterValue.getValue() + "'", Car.class).getResultList();
        if (car.size() != 0)
            return car.get(0);
        return null;
    }

    @Override
    public List<Car> getCarsByCheckpoint(int destinationId) {
        SqlParameterValue sqlParameterValue = new SqlParameterValue(Types.INTEGER, destinationId);
        List<Car> cars = sessionFactory.getCurrentSession().createQuery("from Car where destinationId = '" + sqlParameterValue.getValue() + "'", Car.class).getResultList();
        return cars;
    }
}
