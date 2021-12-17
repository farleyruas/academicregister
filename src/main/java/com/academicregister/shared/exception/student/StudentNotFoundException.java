package com.academicregister.shared.exception.student;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(String id) {
        super(String.format("Student id <%s> was not found.", id));
    }
}
