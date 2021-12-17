package com.academicregister.domain.enrollment;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnrollmentMapper implements RowMapper<Enrollment> {
    public Enrollment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Enrollment enrollment = new Enrollment();
        enrollment.setId(rs.getString("id"));
        enrollment.setCourseId(rs.getString("course"));
        enrollment.setStudentId(rs.getString("student"));
        return enrollment;
    }
}
