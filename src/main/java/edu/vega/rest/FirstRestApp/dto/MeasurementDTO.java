package edu.vega.rest.FirstRestApp.dto;

import edu.vega.rest.FirstRestApp.models.Sensor;
import jakarta.validation.constraints.*;

public class MeasurementDTO {
    @NotNull
    @Min(value = -100)
    @Max(value = 100)
    private Double value;
    @NotNull
    private Boolean raining;
    @NotNull
    private SensorDTO sensor;

    public MeasurementDTO(){}


    public MeasurementDTO(Double value, Boolean raining, SensorDTO sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
