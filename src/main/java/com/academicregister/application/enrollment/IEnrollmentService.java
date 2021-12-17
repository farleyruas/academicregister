package com.academicregister.application.enrollment;

import com.academicregister.domain.enrollment.Enrollment;

import java.util.List;

public interface IEnrollmentService {
    List<Enrollment> getEnrollmentsByStudent();
    List<Enrollment> getEnrollmentsByCourse();
    String createEnrollment(Enrollment enrollment);
}
