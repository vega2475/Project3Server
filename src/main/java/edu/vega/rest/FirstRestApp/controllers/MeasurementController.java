package edu.vega.rest.FirstRestApp.controllers;

import edu.vega.rest.FirstRestApp.Services.MeasurementService;
import edu.vega.rest.FirstRestApp.dto.MeasurementDTO;
import edu.vega.rest.FirstRestApp.util.Exceptions.MeasureResponseError;
import edu.vega.rest.FirstRestApp.util.Exceptions.MeasurementNotCreatedException;
import edu.vega.rest.FirstRestApp.util.Exceptions.MeasurementNotFoundException;
import edu.vega.rest.FirstRestApp.util.Exceptions.MeasurementValidator;
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
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;
    private final MeasurementValidator measurementValidator;

    @Autowired
    public MeasurementController(MeasurementService measurementService, MeasurementValidator measurementValidator) {
        this.measurementService = measurementService;
        this.measurementValidator = measurementValidator;
    }

    @GetMapping()
    public List<MeasurementDTO> getAllMeasurements(){
        return measurementService.getAllMeasurements().stream().map(measurement -> measurementService.convertToDTO(measurement)).toList();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult){
        measurementValidator.validate(measurementService.convertToMeasurement(measurementDTO), bindingResult);
        if(bindingResult.hasErrors()){
            StringBuilder message = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error : errors){
                message.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append(";");
            }
            throw new MeasurementNotCreatedException(message.toString());
        }
        measurementService.save(measurementService.convertToMeasurement(measurementDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/rainyDay")
    public List<MeasurementDTO> showRainyDays(){
        return measurementService.showRainyDay().stream().map(measurement -> measurementService.convertToDTO(measurement)).toList();
    }

    @GetMapping("/rainyDayCount")
    public int countOfRainyDays(){
        return measurementService.rainyDay();
    }

    @ExceptionHandler
    public ResponseEntity<MeasureResponseError> handleException(MeasurementNotCreatedException e){
        MeasureResponseError measureResponseError = new MeasureResponseError(e.getMessage(), new Date().getTime());
        return new ResponseEntity<>(measureResponseError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<MeasureResponseError> handleException(MeasurementNotFoundException e){
        MeasureResponseError measureResponseError = new MeasureResponseError(e.getMessage(), new Date().getTime());
        return new ResponseEntity<>(measureResponseError, HttpStatus.NOT_FOUND);
    }
}
