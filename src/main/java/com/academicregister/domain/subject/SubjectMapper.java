package com.academicregister.domain.subject;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectMapper implements RowMapper<Subject> {
    public Subject mapRow(ResultSet rs, int rowNum) throws SQLException {
        var subject = new Subject();
        subject.setId(rs.getString("id"));
        subject.setTitle(rs.getString("title"));
        return subject;
    }
}
