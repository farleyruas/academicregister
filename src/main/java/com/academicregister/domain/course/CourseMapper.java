package com.academicregister.domain.course;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseMapper implements RowMapper<Course> {
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException{
        var course = new Course();
        course.setId(rs.getString("id"));
        course.setName(rs.getString("name"));
        course.setDescription(rs.getString("description"));
        return course;
    }
}
