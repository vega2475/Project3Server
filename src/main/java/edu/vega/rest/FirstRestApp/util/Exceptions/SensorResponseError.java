package edu.vega.rest.FirstRestApp.util.Exceptions;

import java.util.Date;

public class SensorResponseError {
    private String message;
    private long timestamp;

    public SensorResponseError(String message, long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
