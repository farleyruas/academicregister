package com.academicregister.domain.enrollment;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnrollmentMapper implements RowMapper<Enrollment> {
    public Enrollment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Enrollment enrollment = new Enrollment();
        enrollment.setCourse(rs.getString("course"));
        enrollment.setStudent(rs.getString("student"));
        return enrollment;
    }
}
