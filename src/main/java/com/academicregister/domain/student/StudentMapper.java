package com.academicregister.domain.student;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements RowMapper<Student>{

    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Student.builder()
                .id(rs.getString("id"))
                .name(rs.getString("name"))
                .lastName(rs.getString("last_name"))
                .build();
    }
}
