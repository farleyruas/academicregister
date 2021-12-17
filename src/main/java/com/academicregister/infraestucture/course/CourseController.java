package com.academicregister.infraestucture.course;

import com.academicregister.application.course.ICourseService;
import com.academicregister.domain.course.Course;
import com.academicregister.domain.student.Student;
import com.academicregister.infraestucture.course.create.CourseCreateRequest;
import com.academicregister.infraestucture.course.create.CourseCreateResponse;
import com.academicregister.infraestucture.course.update.CourseUpdateRequest;
import com.academicregister.infraestucture.student.create.StudentCreateRequest;
import com.academicregister.infraestucture.student.create.StudentCreateResponse;
import com.academicregister.infraestucture.student.update.StudentUpdateRequest;
import io.swagger.annotations.ApiParam;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

    @GetMapping("/courses")
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

    @PatchMapping("/course/{id}")
    public ResponseEntity<Void> updateStudent(@PathVariable("id") String id,
                                              @ApiParam(value = "Course to Update", required = true)
                                              @RequestBody CourseUpdateRequest courseUpdateRequest) {
        var course = modelMapper.map(courseUpdateRequest, Course.class);
        course.setId(id);
        service.updateCourse(course);
        return ResponseEntity.ok().header(course.getId()).build();
    }

    @GetMapping("/course/{id}")
    public Course getCourse(@PathVariable String id){
        var course = service.getCourse(id);
        return course;
    }

    @DeleteMapping("/course/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable String id) {
        service.deleteCourse(id);
        return ResponseEntity.ok().header(id).build();
    }
}
