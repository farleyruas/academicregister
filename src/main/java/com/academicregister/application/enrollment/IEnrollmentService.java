package com.academicregister.application.enrollment;

import com.academicregister.domain.enrollment.Enrollment;

import java.util.List;

public interface IEnrollmentService {
    List<Enrollment> getEnrollments();
    List<Enrollment> getEnrollmentsByStudent();
    List<Enrollment> getEnrollmentsByCourse();
    String createEnrollment(Enrollment enrollment);
    void deleteEnrollmentByStudent(String studentId);
    void updateEnrollment(Enrollment enrollment);

}
