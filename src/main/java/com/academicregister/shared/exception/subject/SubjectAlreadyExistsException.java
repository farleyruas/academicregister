package com.academicregister.shared.exception.subject;

public class SubjectAlreadyExistsException extends RuntimeException {
    public SubjectAlreadyExistsException(String id) {
        super(String.format("Subject id <%s> already exists.", id));
    }
}
