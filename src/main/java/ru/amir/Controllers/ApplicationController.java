package ru.amir.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.amir.BusinessLogic.CarBL;
import ru.amir.Configuration.StringToCamera;
import ru.amir.Entities.Car;
import ru.amir.Services.Interfaces.CarService;

@Controller
@RequestMapping("/monitoringSystem/application")
@Scope(value = "request")
public class ApplicationController {

    @Autowired
    private CarService carService;

    @Autowired
    private CarBL carBL;

    @GetMapping("/start")
    public String startVehicle(@RequestParam("carId") int carId) {
        carBL.setCar(carService.getCar(carId));
        Thread thread = new Thread(carBL);
        thread.start();
        return "redirect:/monitoringSystem/cars/";
    }
}
