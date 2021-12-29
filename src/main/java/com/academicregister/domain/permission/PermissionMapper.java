package com.academicregister.domain.permission;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PermissionMapper implements RowMapper<Permission> {
    public Permission mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Permission.builder()
                .role(rs.getString("role"))
                .resource(rs.getString("resource"))
                .build();
    }
}
