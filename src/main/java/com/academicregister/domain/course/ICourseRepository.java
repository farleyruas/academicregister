package com.academicregister.domain.course;

import java.util.List;
import java.util.Optional;

public interface ICourseRepository {
    void save(Course course);
    List<Course> findAll();
    Course findById(String id);
}
