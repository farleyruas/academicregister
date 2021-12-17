package com.academicregister.shared.exception.enrollment;

public class EnrollmentAlreadyExistsException extends RuntimeException {

    public EnrollmentAlreadyExistsException(String courseId, String studentId){
        super(String.format("Enrollment with course id <%s> and student id <%s> already exists.", courseId, studentId));
    }
}
