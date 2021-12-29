package com.academicregister.domain.subject;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectMapper implements RowMapper<Subject> {
    public Subject mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Subject.builder()
            .id(rs.getString("id"))
            .title(rs.getString("title"))
            .build();
    }
}
