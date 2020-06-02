package ru.amir.DAO.Implementations;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.amir.DAO.Interfaces.CameraDAO;
import ru.amir.Entities.SecurityCamera;

import java.util.List;

@Repository
public class CameraDAOImpl implements CameraDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveCamera(SecurityCamera camera) {
        sessionFactory.getCurrentSession().saveOrUpdate(camera);
    }

    @Override
    public List<SecurityCamera> getCameras() {
        List<SecurityCamera> cameras = sessionFactory.getCurrentSession().
                createQuery("from SecurityCamera order by id", SecurityCamera.class).getResultList();
        return cameras;
    }

    @Override
    public SecurityCamera getCamera(int id) {
        return sessionFactory.getCurrentSession().get(SecurityCamera.class, id);
    }

    @Override
    public void deleteCamera(int id) {
        SecurityCamera camera = getCamera(id);
        sessionFactory.getCurrentSession().delete(camera);
    }

    @Override
    public List<SecurityCamera> getCamerasWithoutCheckpoints() {
        List<SecurityCamera> cameras = sessionFactory.getCurrentSession().
                createQuery("from SecurityCamera where checkpointId = 0 order by id", SecurityCamera.class).getResultList();
        return cameras;
    }
}
