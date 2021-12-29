package com.academicregister.infraestucture.enrollment;

import com.academicregister.application.enrollment.IEnrollmentService;
import com.academicregister.domain.enrollment.Enrollment;
import com.academicregister.infraestucture.enrollment.create.EnrollmentCreateRequest;
import com.academicregister.infraestucture.enrollment.create.EnrollmentCreateResponse;
import io.swagger.annotations.ApiParam;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<EnrollmentCreateResponse> createEnrollment(
            @ApiParam(value = "Enrollment to Create", required = true)
            @RequestBody EnrollmentCreateRequest enrollmentCreateRequest) {
        var enrollment = modelMapper.map(enrollmentCreateRequest, Enrollment.class);
        var id = enrollment.getStudentId().concat(enrollment.getCourseId());
        enrollment.setId(id);
        String enrollmentCreatedId = service.createEnrollment(enrollment).getCourseId();
        return new ResponseEntity<>(new EnrollmentCreateResponse(enrollmentCreatedId), HttpStatus.CREATED);
    }
}
