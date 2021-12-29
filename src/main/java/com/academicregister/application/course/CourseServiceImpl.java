package com.academicregister.application.course;

import com.academicregister.domain.course.Course;
import com.academicregister.domain.course.ICourseRepository;
import com.academicregister.shared.exception.course.CourseAlreadyExistsException;
import com.academicregister.shared.exception.course.CourseNotFoundException;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService{

    private final ICourseRepository courseRepository;

    CourseServiceImpl(ICourseRepository repository){ this.courseRepository = repository; }

    @Override
    public List<Course> getCourses() {
        var courses = courseRepository.findAll();
        return courses;
    }

    @Override
    public String createCourse(Course course) {

        Option<Course> opCourse = Option.of(courseRepository.findById(course.getId()));
        opCourse.exists( rs -> {
           throw new CourseAlreadyExistsException(course.getId());
        });

        courseRepository.save(course);
        return course.getId();
    }

    @Override
    public Course getCourse(String id) {

        Try<Course> rsCourse = Try.of(() -> courseRepository.findById(id));
        return rsCourse.getOrElseThrow(throwable -> {
            throw new CourseNotFoundException(id);
        });
    }
}
