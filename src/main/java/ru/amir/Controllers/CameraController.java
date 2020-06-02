package ru.amir.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.amir.Entities.Checkpoint;
import ru.amir.Entities.SecurityCamera;
import ru.amir.Services.Interfaces.CameraService;
import ru.amir.Services.Interfaces.CheckpointService;

import java.util.List;

@Controller
@RequestMapping("/monitoringSystem/cameras")
public class CameraController {

    @Autowired
    private CameraService cameraService;

    @Autowired
    private CheckpointService checkpointService;

    @GetMapping("/")
    public String getCamerasList(Model model) {
        model.addAttribute("cameras", cameraService.getCameras());
        return "list-cameras";
    }

    @GetMapping("/addCameraForm")
    public String addCameraForm(Model model) {
        model.addAttribute("camera", new SecurityCamera());
        return "camera-form";
    }

    @PostMapping("/saveCamera")
    public String saveCamera(@ModelAttribute("camera") SecurityCamera camera) {
        cameraService.saveCamera(camera);
        return "redirect:/monitoringSystem/cameras/";
    }

    @GetMapping("deleteCamera")
    public String deleteCamera(@RequestParam("cameraId") int id) {
        Checkpoint checkpoint = checkpointService.getCheckpoint(cameraService.getCamera(id).getCheckpointId());
        if (cameraService.getCamera(id).isEntryCamera())
            checkpoint.setEntryCamera(null);
        else
            checkpoint.setExitCamera(null);
        checkpointService.saveCheckpoint(checkpoint);
        cameraService.deleteCamera(id);
        return "redirect:/monitoringSystem/cameras/";
    }

    @GetMapping("updateCameraForm")
    public String updateCameraForm(Model model, @RequestParam("cameraId") int id) {
        model.addAttribute("camera", cameraService.getCamera(id));
        return "camera-form";
    }

}
