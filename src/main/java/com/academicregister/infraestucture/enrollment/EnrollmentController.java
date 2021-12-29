package com.academicregister.infraestucture.enrollment;

import com.academicregister.application.enrollment.IEnrollmentService;
import com.academicregister.domain.course.Course;
import com.academicregister.domain.enrollment.Enrollment;
import com.academicregister.infraestucture.enrollment.create.EnrollmentCreateRequest;
import com.academicregister.infraestucture.enrollment.create.EnrollmentCreateResponse;
import io.swagger.annotations.ApiParam;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class EnrollmentController {

    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    private final IEnrollmentService service;

    @Autowired
    public EnrollmentController(IEnrollmentService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/enrollment")
    public ResponseEntity<Void> createEnrollment(
            @ApiParam(value = "Enrollment to Create", required = true)
            @RequestBody EnrollmentCreateRequest enrollmentCreateRequest) {
        var enrollment = modelMapper.map(enrollmentCreateRequest, Enrollment.class);
        service.createEnrollment(enrollment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/enrollment")
    public ResponseEntity<List<Course>> enrollmentsByStudent(@RequestParam("studentId") String studentId) {
        var enrollmentList = service.getEnrollmentsByStudent(studentId);
        return new ResponseEntity<List<Course>>(enrollmentList, HttpStatus.OK);
    }
}
