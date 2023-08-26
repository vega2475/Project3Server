package edu.vega.rest.FirstRestApp.controllers;

import edu.vega.rest.FirstRestApp.Services.SensorService;
import edu.vega.rest.FirstRestApp.dto.SensorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorService sensorService;

    @Autowired
    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping()
    public List<SensorDTO> getAll(){
        return sensorService.getAllSensors().stream().map(sensor -> sensorService.convertToSensorDTO(sensor)).toList();
    }
}
