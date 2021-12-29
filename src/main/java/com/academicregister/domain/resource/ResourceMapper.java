package com.academicregister.domain.resource;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResourceMapper implements RowMapper<Resource> {
    public Resource mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Resource.builder()
                .id(rs.getString("id"))
                .url(rs.getString("url"))
                .build();
    }
}
