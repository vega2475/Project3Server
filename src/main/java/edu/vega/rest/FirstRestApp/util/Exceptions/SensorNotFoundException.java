package edu.vega.rest.FirstRestApp.util.Exceptions;

public class SensorNotFoundException extends RuntimeException{
    public SensorNotFoundException(String message){
        super(message);
    }
}
