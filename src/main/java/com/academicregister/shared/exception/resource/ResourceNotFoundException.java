package com.academicregister.shared.exception.resource;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String text) {
        super(String.format("The resource %s was not found", text));
    }
}
