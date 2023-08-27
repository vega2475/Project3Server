package edu.vega.rest.FirstRestApp.controllers;

import edu.vega.rest.FirstRestApp.Services.SensorService;
import edu.vega.rest.FirstRestApp.dto.SensorDTO;
import edu.vega.rest.FirstRestApp.models.Sensor;
import edu.vega.rest.FirstRestApp.util.Exceptions.SensorNotCreatedException;
import edu.vega.rest.FirstRestApp.util.Exceptions.SensorNotFoundException;
import edu.vega.rest.FirstRestApp.util.Exceptions.SensorResponseError;
import edu.vega.rest.FirstRestApp.util.SensorValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorService sensorService;
    private final SensorValidator sensorValidator;

    @Autowired
    public SensorController(SensorService sensorService, SensorValidator sensorValidator) {
        this.sensorService = sensorService;
        this.sensorValidator = sensorValidator;
    }

    @GetMapping()
    public List<SensorDTO> getAll(){
        return sensorService.getAllSensors().stream().map(sensor -> sensorService.convertToSensorDTO(sensor)).toList();
    }

    @GetMapping("/{id}")
    public SensorDTO getSensor(@PathVariable("id") int id){
        return sensorService.convertToSensorDTO(sensorService.findOne(id));
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult){
        Sensor sensor = sensorService.convertToSensor(sensorDTO);
        sensorValidator.validate(sensor, bindingResult);
        if(bindingResult.hasErrors()){
            StringBuilder message = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error : errors){
                message.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append(";");
            }
            throw new SensorNotCreatedException(message.toString());
        }
        sensorService.save(sensor);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<SensorResponseError> handleException(SensorNotFoundException e){
        SensorResponseError sensorResponseError = new SensorResponseError(e.getMessage(), new Date().getTime());
        return new  ResponseEntity<>(sensorResponseError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<SensorResponseError> handleException(SensorNotCreatedException e){
        SensorResponseError sensorResponseError = new SensorResponseError(e.getMessage(), new Date().getTime());
        return new ResponseEntity<>(sensorResponseError, HttpStatus.BAD_REQUEST);
    }
}
