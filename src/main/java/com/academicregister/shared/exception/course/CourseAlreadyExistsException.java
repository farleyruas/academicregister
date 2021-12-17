package com.academicregister.shared.exception.course;

public class CourseAlreadyExistsException extends RuntimeException {
    public CourseAlreadyExistsException(String id){
        super(String.format("Student id <%s> already exists.", id));
    }
}
