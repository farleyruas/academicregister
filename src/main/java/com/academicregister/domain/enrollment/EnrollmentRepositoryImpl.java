package com.academicregister.domain.enrollment;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EnrollmentRepositoryImpl implements IEnrollmentRepository{

    private JdbcTemplate template;

    public EnrollmentRepositoryImpl(JdbcTemplate template){
        this.template = template;
    }

    @Transactional
    @Override
    public void save(Enrollment enrollment) {
        template.update("INSERT INTO ENROLLMENTS VALUES (?, ?, ?)",
                enrollment.getId(), enrollment.getStudentId(), enrollment.getCourseId());
    }

    @Override
    public List<Enrollment> findByStudent(String studentId) {
        String query = "SELECT * FROM ENROLLMENTS WHERE STUDENT = ?";
        var result = template.query(query, new EnrollmentMapper(), studentId);
        return result;
    }

    @Override
    public List<Enrollment> findByCourse(String courseId) {
        String query = "SELECT * FROM ENROLLMENTS WHERE COURSE = ?";
        var result = template.query(query, new EnrollmentMapper(), courseId);
        return result;
    }

    @Override
    public Enrollment findByCourseAndStudent(String courseId, String studentId) {
        String query = "SELECT * FROM ENROLLMENTS WHERE COURSE = ? AND STUDENT = ?";
        var result = template.query(query, new EnrollmentMapper(), courseId, studentId);
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

}
