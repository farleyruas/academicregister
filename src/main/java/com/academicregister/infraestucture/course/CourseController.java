package com.academicregister.infraestucture.course;

import com.academicregister.application.course.ICourseService;
import com.academicregister.domain.course.Course;
import com.academicregister.infraestucture.course.create.CourseCreateRequest;
import com.academicregister.infraestucture.course.create.CourseCreateResponse;
import io.swagger.annotations.ApiParam;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    @Autowired
    private final ICourseService service;

    @Autowired
    private final ModelMapper modelMapper;

    public CourseController(ICourseService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all-courses")
    public List<Course> getCourses(){
        var courses = service.getCourses();
        return courses;
    }

    @PostMapping("/course")
    public ResponseEntity<CourseCreateResponse> createCourse(
            @ApiParam(value = "Course to Create", required = true)
            @RequestBody CourseCreateRequest courseCreateRequest) {
        var course = modelMapper.map(courseCreateRequest, Course.class);
        String courseCreatedId;
        courseCreatedId = service.createCourse(course);
        return new ResponseEntity<>(new CourseCreateResponse(courseCreatedId), HttpStatus.CREATED);
    }
}
