package edu.vega.rest.FirstRestApp.util.Exceptions;

public class MeasurementNotFoundException extends RuntimeException{
    public MeasurementNotFoundException(String message){
        super(message);
    }
}
