package ru.maconconsulting.librarybackend.utils.exceptions.parameters;

public class SegmentNotFoundException extends RuntimeException {
    public SegmentNotFoundException(String message) {
        super(message);
    }
}
