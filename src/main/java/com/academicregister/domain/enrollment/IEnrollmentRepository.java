package com.academicregister.domain.enrollment;

import java.util.List;
import java.util.Optional;

public interface IEnrollmentRepository {
    void save(Enrollment enrollment);
    List<Enrollment> findByStudent(String studentId);
    List<Enrollment> findByCourse(String courseId);
    Enrollment findByCourseAndStudent(String courseId, String studentId);
}
