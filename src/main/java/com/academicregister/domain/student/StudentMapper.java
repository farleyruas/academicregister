package com.academicregister.domain.student;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements RowMapper<Student>{
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student student = new Student();
        student.setId(rs.getString("id"));
        student.setName(rs.getString("name"));
        student.setLastName(rs.getString("last_name"));
        return student;
    }
}
