package edu.vega.rest.FirstRestApp.dto;

import edu.vega.rest.FirstRestApp.models.Sensor;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class MeasurementDTO {
    @Size(min = -100, max = 100)
    private double value;
    @NotEmpty
    private boolean raining;

    @NotEmpty
    private Sensor sensor;

    public MeasurementDTO(double value, boolean raining, Sensor sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public MeasurementDTO(){}

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
