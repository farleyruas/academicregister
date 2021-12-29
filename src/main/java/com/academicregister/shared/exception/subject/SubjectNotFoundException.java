package com.academicregister.shared.exception.subject;

public class SubjectNotFoundException extends RuntimeException{
    public SubjectNotFoundException(String text) {
        super(String.format("The subject %s was not found", text));
    }
}
