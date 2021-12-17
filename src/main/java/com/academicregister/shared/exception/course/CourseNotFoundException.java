package com.academicregister.shared.exception.course;

public class CourseNotFoundException extends RuntimeException{
    public CourseNotFoundException(String id) {
        super(String.format("Course id <%s> was not found.", id));
    }
}


