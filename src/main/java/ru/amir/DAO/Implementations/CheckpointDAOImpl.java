package ru.amir.DAO.Implementations;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.amir.DAO.Interfaces.CheckpointDAO;
import ru.amir.Entities.Checkpoint;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CheckpointDAOImpl implements CheckpointDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveCheckpoint(Checkpoint checkpoint) {
        sessionFactory.getCurrentSession().saveOrUpdate(checkpoint);
    }

    @Override
    public void deleteCheckpoint(int id) {
        Checkpoint checkpoint = getCheckpoint(id);
        sessionFactory.getCurrentSession().delete(checkpoint);
    }

    @Override
    public List<Checkpoint> getCheckpoints() {
        List<Checkpoint> checkpoints = sessionFactory.getCurrentSession().createQuery("from Checkpoint order by id").getResultList();
        return checkpoints;
    }

    @Override
    public Checkpoint getCheckpoint(int id) {
        return sessionFactory.getCurrentSession().get(Checkpoint.class, id);
    }

    @Override
    public List<Integer> getCheckpointsLocations() {
        List<Integer> checkpointsLocations = new ArrayList<>();
        for (Checkpoint checkpoint : getCheckpoints()) {
            checkpointsLocations.add(checkpoint.getLocation());
        }
        return checkpointsLocations;
    }

}
