package edu.vega.rest.FirstRestApp.util;

public class SensorNotFoundException extends RuntimeException{
    public SensorNotFoundException(String message){
        super(message);
    }
}
