package ru.amir.Services.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.amir.DAO.Interfaces.CheckpointDAO;
import ru.amir.Entities.Checkpoint;
import ru.amir.Services.Interfaces.CheckpointService;

import java.util.Collections;
import java.util.List;

@Service
public class CheckpointServiceImpl implements CheckpointService {

    @Autowired
    private CheckpointDAO checkpointDAO;

    @Override
    @Transactional
    public void saveCheckpoint(Checkpoint checkpoint) {
        checkpointDAO.saveCheckpoint(checkpoint);
    }

    @Override
    @Transactional
    public void deleteCheckpoint(int id) {
        checkpointDAO.deleteCheckpoint(id);
    }

    @Override
    @Transactional
    public List<Checkpoint> getCheckpoints() {
        return checkpointDAO.getCheckpoints();
    }

    @Override
    @Transactional
    public Checkpoint getCheckpoint(int id) {
        return checkpointDAO.getCheckpoint(id);
    }

    @Override
    @Transactional
    public List<Integer> getCheckpointsLocations() {
        List<Integer> list = checkpointDAO.getCheckpointsLocations();
        Collections.sort(list);
        return list;
    }
}
