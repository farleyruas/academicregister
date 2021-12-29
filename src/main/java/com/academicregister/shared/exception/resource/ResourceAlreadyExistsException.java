package com.academicregister.shared.exception.resource;

public class ResourceAlreadyExistsException extends RuntimeException{
    public ResourceAlreadyExistsException(String text) {
        super(String.format("The resource %s already exists.", text));
    }
}
