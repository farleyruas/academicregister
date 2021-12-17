package com.academicregister.domain.content;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContentMapper implements RowMapper<Content> {
    public Content mapRow(ResultSet rs, int rowNum) throws SQLException {
        var content = new Content();
        content.setCourseId(rs.getString("course"));
        content.setSubjectId(rs.getString("subject"));
        return content;
    }
}
