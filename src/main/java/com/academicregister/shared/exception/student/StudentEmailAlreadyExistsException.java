package com.academicregister.shared.exception.student;

public class StudentEmailAlreadyExistsException extends RuntimeException {

    public StudentEmailAlreadyExistsException(String id) {
        super(String.format("Student email <%s> already exists.", id));
    }
}

