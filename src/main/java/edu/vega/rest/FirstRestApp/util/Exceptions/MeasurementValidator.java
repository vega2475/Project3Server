package edu.vega.rest.FirstRestApp.util.Exceptions;

import edu.vega.rest.FirstRestApp.Services.SensorService;
import edu.vega.rest.FirstRestApp.models.Measurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class MeasurementValidator implements Validator {
    private final SensorService sensorService;

    @Autowired
    public MeasurementValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurement measurement = (Measurement) target;

        if(sensorService.findSensorByName(measurement.getSensor().getName()).isEmpty()){
            errors.rejectValue("sensor", "", "Sensor with this name doesnt exist");
        }
    }
}
