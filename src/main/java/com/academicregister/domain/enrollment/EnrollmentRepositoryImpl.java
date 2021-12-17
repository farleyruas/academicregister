package com.academicregister.domain.enrollment;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class EnrollmentRepositoryImpl implements IEnrollmentRepository{

    private JdbcTemplate template;

    public EnrollmentRepositoryImpl(JdbcTemplate template){
        this.template = template;
    }
    @Override
    public void save(Enrollment enrollment) {
        template.update("INSERT INTO ENROLLMENTS VALUES (?, ?, ?)",
                enrollment.getId(), enrollment.getStudentId(), enrollment.getCourseId());
    }

    @Override
    public List<Enrollment> findAll() {
        return template.query("SELECT * FROM ENROLLMENTS", new EnrollmentMapper());
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
    public void deleteStudentById(String studentId) {
        template.update("DELETE FROM ENROLLMENTS WHERE STUDENT = ?", studentId);
    }
}
