package ru.maconconsulting.librarybackend.utils.exceptions.content;

public class PublicationNotFoundException extends RuntimeException {

    public PublicationNotFoundException(String message) {
        super(message);
    }
}
