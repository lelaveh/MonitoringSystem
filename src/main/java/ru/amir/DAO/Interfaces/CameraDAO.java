package ru.amir.DAO.Interfaces;

import ru.amir.Entities.SecurityCamera;

import java.util.List;

public interface CameraDAO {

    public List<SecurityCamera> getCameras();

    public SecurityCamera getCamera(int id);

    public void deleteCamera(int id);

    public void saveCamera(SecurityCamera camera);

    public List<SecurityCamera> getCamerasWithoutCheckpoints();
}
