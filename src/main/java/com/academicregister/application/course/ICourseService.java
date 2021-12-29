package com.academicregister.application.course;

import com.academicregister.domain.course.Course;

import java.util.List;

public interface ICourseService {

    List<Course> getCourses();
    String createCourse(Course course);
    Course getCourse(String id);
}
