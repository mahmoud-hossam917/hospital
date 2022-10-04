package com.example.hospital.Error;

public class DaplicateException extends RuntimeException {

    public DaplicateException() {
    }

    public DaplicateException(String message) {
        super(message);
    }
}
