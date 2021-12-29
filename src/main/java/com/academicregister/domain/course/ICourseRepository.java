package com.academicregister.domain.course;

import java.util.List;

public interface ICourseRepository {
    void save(Course course);
    List<Course> findAll();
    Course findByName(String name);
    Course findById(String id);

}
