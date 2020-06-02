package ru.amir.Services.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.amir.DAO.Interfaces.CameraDAO;
import ru.amir.Entities.SecurityCamera;
import ru.amir.Services.Interfaces.CameraService;

import java.util.List;

@Service
public class CameraServiceImpl implements CameraService {

    @Autowired
    private CameraDAO cameraDAO;

    @Transactional
    @Override
    public List<SecurityCamera> getCameras() {
        return cameraDAO.getCameras();
    }

    @Transactional
    @Override
    public SecurityCamera getCamera(int id) {
        return cameraDAO.getCamera(id);
    }

    @Transactional
    @Override
    public void deleteCamera(int id) {
        cameraDAO.deleteCamera(id);
    }

    @Transactional
    @Override
    public void saveCamera(SecurityCamera camera) {
        cameraDAO.saveCamera(camera);
    }

    @Transactional
    @Override
    public List<SecurityCamera> getCamerasWithoutCheckpoints() {
        return cameraDAO.getCamerasWithoutCheckpoints();
    }
}
