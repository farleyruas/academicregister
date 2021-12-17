package com.academicregister.infraestucture.student;

import com.academicregister.application.student.IStudentService;
import com.academicregister.domain.student.Student;
import com.academicregister.infraestucture.student.create.StudentCreateRequest;
import com.academicregister.infraestucture.student.create.StudentCreateResponse;
import io.swagger.annotations.ApiParam;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final ModelMapper modelMapper;

    @Autowired
    private final IStudentService service;

    @Autowired
    public StudentController(IStudentService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/students")
    public List<Student> getStudents(){
        var students = service.getStudents();
        return students;
    }

    @PostMapping("/students")
    public ResponseEntity<StudentCreateResponse> createStudent(
            @ApiParam(value = "Student to Create", required = true)
            @RequestBody StudentCreateRequest studentCreateRequest) {
        var student = modelMapper.map(studentCreateRequest, Student.class);
        var studentCreatedId = service.createStudent(student).getId();
        return new ResponseEntity<>(new StudentCreateResponse(studentCreatedId), HttpStatus.CREATED);
    }

}
