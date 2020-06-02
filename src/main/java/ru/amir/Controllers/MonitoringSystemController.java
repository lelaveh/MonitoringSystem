package ru.amir.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/monitoringSystem")
public class MonitoringSystemController {

    @GetMapping("/")
    public String homePage() {
        return "home";
    }
}
