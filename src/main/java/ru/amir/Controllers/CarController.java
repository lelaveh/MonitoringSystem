package ru.amir.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.amir.Entities.Car;
import ru.amir.Services.Interfaces.CarService;
import ru.amir.Services.Interfaces.CheckpointService;

import javax.validation.Valid;

@Controller
@RequestMapping("/monitoringSystem/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private CheckpointService checkpointService;

    @GetMapping("/")
    public String getCarsList(Model model) {
        model.addAttribute("cars", carService.getCars());
        return "list-cars";
    }

    @GetMapping("/addCarForm")
    public String addCarForm(Model model) {
        model.addAttribute("car", new Car());
        model.addAttribute("isUpdate", Boolean.FALSE);
        model.addAttribute("checkpoints", checkpointService.getCheckpoints());

        return "car-form";
    }

    @PostMapping("/saveCar")
    public String saveCar(@Valid @ModelAttribute("car") Car car, BindingResult bindingResult,
                          @RequestParam("isUpdate") Boolean isUpdate, Model model) {
        if (!isUpdate) {
            Car duplicate = carService.getCarByLP(car.getRegNum());
            if (duplicate != null) {
                FieldError fieldError = new FieldError("car", "regNum",
                        "License plate number is not unique");
                bindingResult.addError(fieldError);
            }
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("checkpoints", checkpointService.getCheckpoints());
            if (isUpdate) {
                model.addAttribute("isUpdate", Boolean.TRUE);
            } else model.addAttribute("isUpdate", Boolean.FALSE);
            return "car-form";
        }
        carService.saveCar(car);
        return "redirect:/monitoringSystem/cars/";
    }

    @GetMapping("/updateCarForm")
    public String updateCarForm(@RequestParam("carId") int carId, Model model) {
        Car car = carService.getCar(carId);
        model.addAttribute("checkpoints", checkpointService.getCheckpoints());
        model.addAttribute("car", car);
        model.addAttribute("isUpdate", Boolean.TRUE);
        return "car-form";
    }

    @GetMapping("/deleteCar")
    public String deleteCar(@RequestParam("carId") int carId) {
        carService.deleteCar(carId);
        return "redirect:/monitoringSystem/cars/";
    }
}
