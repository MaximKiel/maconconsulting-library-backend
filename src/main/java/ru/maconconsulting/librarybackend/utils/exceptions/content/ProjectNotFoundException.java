package ru.maconconsulting.librarybackend.utils.exceptions.content;

public class ProjectNotFoundException extends RuntimeException {

    public ProjectNotFoundException(String message) {
        super(message);
    }
}
