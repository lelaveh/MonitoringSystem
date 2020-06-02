package ru.amir.Services.Interfaces;


import org.springframework.beans.factory.annotation.Autowired;
import ru.amir.DAO.Interfaces.CameraDAO;
import ru.amir.Entities.SecurityCamera;

import java.util.List;

public interface CameraService {

    public void saveCamera(SecurityCamera camera);

    public SecurityCamera getCamera(int id);

    public List<SecurityCamera> getCameras();

    public void deleteCamera(int id);

    public List<SecurityCamera> getCamerasWithoutCheckpoints();
}
