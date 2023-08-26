package edu.vega.rest.FirstRestApp.util;

public class MeasurementNotFoundException extends RuntimeException{
    public MeasurementNotFoundException(String message){
        super(message);
    }
}
