package com.academicregister.application.course;

import com.academicregister.domain.course.Course;
import com.academicregister.domain.course.ICourseRepository;
import com.academicregister.shared.exception.course.CourseAlreadyExistsException;
import com.academicregister.shared.exception.course.CourseNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService{

    private final ICourseRepository repository;

    CourseServiceImpl(ICourseRepository repository){ this.repository = repository; }

    @Override
    public List<Course> getCourses() {
        var courses = repository.findAll();
        return courses;
    }

    @Override
    public String createCourse(Course course) {
        var optionalCourse = repository.findById(course.getId());
        if (optionalCourse.isPresent()) {
            throw new CourseAlreadyExistsException(course.getId());
        }
        repository.save(course);
        return course.getId();
    }

    @Override
    public void updateCourse(Course course){
        var optionalCourse = repository.findById(course.getId());
        if (!optionalCourse.isPresent()) {
            throw new CourseNotFoundException(course.getId());
        }
        repository.deleteById(course.getId());
        repository.save(course);
    }

    @Override
    public Course getCourse(String id) {
        var optionalCourse = repository.findById(id);
        if (!optionalCourse.isPresent()) {
            throw new CourseNotFoundException(id);
        }
        return optionalCourse.get();
    }

    @Override
    public void deleteCourse(String id) {
        repository.deleteById(id);
    }
}
