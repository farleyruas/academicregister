package com.academicregister.infraestucture.subject;

import com.academicregister.application.subject.ISubjectService;
import com.academicregister.domain.subject.Subject;
import com.academicregister.infraestucture.subject.create.SubjectCreateRequest;
import com.academicregister.infraestucture.subject.create.SubjectCreateResponse;
import io.swagger.annotations.ApiParam;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class SubjectController {

    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    private final ISubjectService subjectService;

    @Autowired
    public SubjectController(ISubjectService subjectService, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.subjectService = subjectService;
    }

    @PostMapping("/subject")
    public ResponseEntity<SubjectCreateResponse> createSubject(
            @ApiParam(value = "Subject to create", required = true)
            @RequestBody SubjectCreateRequest request) {
        var id = UUID.randomUUID().toString();
        Subject subject = new Subject();
        subject.setId(id);
        subject.setTitle(request.getTitle());
        var subjectCreatedId = subjectService.createSubject(subject).getId();
        return new ResponseEntity<>(new SubjectCreateResponse(subjectCreatedId), HttpStatus.CREATED);
    }

}
