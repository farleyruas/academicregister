package com.academicregister.application.enrollment;

import com.academicregister.domain.course.Course;
import com.academicregister.domain.enrollment.Enrollment;

import java.util.List;

public interface IEnrollmentService {
    List<Course> getEnrollmentsByStudent(String studentId);
    List<Enrollment> getEnrollmentsByCourse(String course);
    void createEnrollment(Enrollment enrollment);
}
