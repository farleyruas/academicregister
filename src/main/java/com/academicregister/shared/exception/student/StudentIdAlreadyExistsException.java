package com.academicregister.shared.exception.student;

public class StudentIdAlreadyExistsException extends RuntimeException {

    public StudentIdAlreadyExistsException(String id) {
        super(String.format("Student id <%s> already exists.", id));
    }
}

