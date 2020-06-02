package ru.amir.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.amir.Entities.Car;
import ru.amir.Entities.Checkpoint;
import ru.amir.Entities.SecurityCamera;
import ru.amir.Services.Interfaces.CameraService;
import ru.amir.Services.Interfaces.CarService;
import ru.amir.Services.Interfaces.CheckpointService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/monitoringSystem/checkpoints")
public class CheckpointController {

    @Autowired
    private CheckpointService checkpointService;

    @Autowired
    private CameraService cameraService;

    @Autowired
    private CarService carService;

    @GetMapping("/")
    public String getCheckpointsList(Model model) {
        model.addAttribute("checkpoints", checkpointService.getCheckpoints());
        return "list-checkpoints";
    }

    @GetMapping("/addCheckpointForm")
    public String addCheckpointForm(Model model) {
        model.addAttribute("availableCameras", cameraService.getCamerasWithoutCheckpoints());
        model.addAttribute("checkpoint", new Checkpoint());
        model.addAttribute("entryCameraWasPresent", Boolean.FALSE);
        model.addAttribute("exitCameraWasPresent", Boolean.FALSE);
        model.addAttribute("entryCameraId", 0);
        model.addAttribute("exitCameraId", 0);
        return "checkpoint-form";
    }

    @PostMapping("/saveCheckpoint")
    public String saveCheckpoint(@Valid @ModelAttribute("checkpoint") Checkpoint checkpoint, BindingResult bindingResult,
                                 @RequestParam("entryCameraWasPresent") Boolean entryCameraWasPresent, @RequestParam("exitCameraWasPresent") Boolean exitCameraWasPresent,
                                 @RequestParam("entryCameraId") int entryCameraId, @RequestParam("exitCameraId") int exitCameraId, Model model) {
        if (((checkpoint.getExitCamera() != null) && (checkpoint.getEntryCamera() != null)) && (checkpoint.getEntryCamera().getId() == checkpoint.getExitCamera().getId())) {
            FieldError fieldError = new FieldError("checkpoint", "exitCamera", "Entry and exit cameras cannot be the same");
            bindingResult.addError(fieldError);
        }
        if (bindingResult.hasErrors()) {
            List<SecurityCamera> availableCameras= cameraService.getCamerasWithoutCheckpoints();
            if (checkpoint.getEntryCamera() != null) {
                availableCameras.add(checkpoint.getEntryCamera());
                model.addAttribute("entryCameraId", checkpoint.getEntryCamera().getId());
            } else model.addAttribute("entryCameraId", 0);
            if (checkpoint.getExitCamera() != null) {
                availableCameras.add(checkpoint.getExitCamera());
                model.addAttribute("exitCameraId", checkpoint.getExitCamera().getId());
            } else model.addAttribute("exitCameraId", 0);

            if (entryCameraWasPresent)
                model.addAttribute("entryCameraWasPresent", Boolean.TRUE);
            else
                model.addAttribute("entryCameraWasPresent", Boolean.FALSE);

            if (exitCameraWasPresent)
                model.addAttribute("exitCameraWasPresent", Boolean.TRUE);
            else
                model.addAttribute("exitCameraWasPresent", Boolean.FALSE);
            model.addAttribute("availableCameras", availableCameras);
            return "checkpoint-form";
        }
        checkpointService.saveCheckpoint(checkpoint);

        // изменение checkpointId бывшей передней камеры
        if (entryCameraWasPresent && ((checkpoint.getEntryCamera() == null) || (entryCameraId != checkpoint.getEntryCamera().getId()))) {
            SecurityCamera formerEntryCamera = cameraService.getCamera(entryCameraId);
            formerEntryCamera.setCheckpointId(0);
            formerEntryCamera.setEntryCamera(null);
            cameraService.saveCamera(formerEntryCamera);
        }

        // изменение checkpointId бывшей задней камеры
        if (exitCameraWasPresent && ((checkpoint.getExitCamera() == null) || (exitCameraId != checkpoint.getExitCamera().getId()))) {
            SecurityCamera formerExitCamera = cameraService.getCamera(exitCameraId);
            formerExitCamera.setCheckpointId(0);
            formerExitCamera.setEntryCamera(null);
            cameraService.saveCamera(formerExitCamera);
        }


        if (checkpoint.getEntryCamera() != null) {
            SecurityCamera entryCamera = cameraService.getCamera(checkpoint.getEntryCamera().getId());
            entryCamera.setCheckpointId(checkpoint.getId());
            entryCamera.setEntryCamera(true);
            cameraService.saveCamera(entryCamera);
        }

        if (checkpoint.getExitCamera() != null) {
            SecurityCamera exitCamera = cameraService.getCamera(checkpoint.getExitCamera().getId());
            exitCamera.setCheckpointId(checkpoint.getId());
            exitCamera.setEntryCamera(false);
            cameraService.saveCamera(exitCamera);
        }

        return "redirect:/monitoringSystem/checkpoints/";
    }

    @GetMapping("/updateCheckpointForm")
    public String updateCheckpointForm(@RequestParam("checkpointId") int checkpointId, Model model) {
        Checkpoint checkpoint = checkpointService.getCheckpoint(checkpointId);
        model.addAttribute("checkpoint", checkpoint);
        SecurityCamera entryCamera = checkpointService.getCheckpoint(checkpointId).getEntryCamera();
        SecurityCamera exitCamera = checkpointService.getCheckpoint(checkpointId).getExitCamera();
        List<SecurityCamera> cameras = cameraService.getCamerasWithoutCheckpoints();
        if (entryCamera != null) {
            cameras.add(entryCamera);
            model.addAttribute("entryCameraWasPresent", Boolean.TRUE);
            model.addAttribute("entryCameraId", checkpoint.getEntryCamera().getId());
        } else {
            model.addAttribute("entryCameraWasPresent", Boolean.FALSE);
            model.addAttribute("entryCameraId", 0);
        }
        if (exitCamera != null) {
            cameras.add(exitCamera);
            model.addAttribute("exitCameraWasPresent", Boolean.TRUE);
            model.addAttribute("exitCameraId", checkpoint.getExitCamera().getId());
        } else {
            model.addAttribute("exitCameraWasPresent", Boolean.FALSE);
            model.addAttribute("exitCameraId", 0);
        }
        model.addAttribute("availableCameras", cameras);
        return "checkpoint-form";
    }

    @GetMapping("/deleteCheckpoint")
    public String deleteCheckpoint(@RequestParam("checkpointId") int checkpointId) {
        Checkpoint checkpoint = checkpointService.getCheckpoint(checkpointId);
        SecurityCamera camera = checkpoint.getExitCamera();
        if (camera != null) {
            camera.setCheckpointId(0);
            cameraService.saveCamera(camera);
        }
        camera = checkpoint.getEntryCamera();
        if (camera != null) {
            camera.setCheckpointId(0);
            cameraService.saveCamera(camera);
        }
        List<Car> cars = carService.getCarsByCheckpoint(checkpointId);
        if (cars.size() != 0)
            for (Car car : cars) {
                car.setDestinationId(0);
                carService.saveCar(car);
            }
        checkpointService.deleteCheckpoint(checkpointId);
        return "redirect:/monitoringSystem/checkpoints/";
    }
}
