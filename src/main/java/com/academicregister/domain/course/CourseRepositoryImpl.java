package com.academicregister.domain.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepositoryImpl implements ICourseRepository{

    private JdbcTemplate template;

    public CourseRepositoryImpl(JdbcTemplate template){
        this.template = template;
    }

    @Override
    public void save(Course course) {
        template.update("INSERT INTO COURSES VALUES (?, ?, ?)",
                course.getId(), course.getName(), course.getDescription());
    }

    @Override
    public List<Course> findAll() {
        return template.query("SELECT * FROM COURSES", new CourseMapper());
    }

    @Override
    public Optional<Course> findById(String id) {
        String query = "SELECT * FROM COURSES WHERE ID = ?";
        var result = template.query(query, new CourseMapper(), id);
        if (result.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(result.get(0));
    }

    @Override
    public void deleteById(String id) {
        template.update("DELETE FROM COURSES WHERE ID = ?", id);
    }
}
