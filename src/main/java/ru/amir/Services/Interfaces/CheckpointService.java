package ru.amir.Services.Interfaces;

import ru.amir.Entities.Checkpoint;

import java.util.List;

public interface CheckpointService {

    public void saveCheckpoint(Checkpoint checkpoint);

    public void deleteCheckpoint(int id);

    public List<Checkpoint> getCheckpoints();

    public Checkpoint getCheckpoint(int id);

    public List<Integer> getCheckpointsLocations();

}
