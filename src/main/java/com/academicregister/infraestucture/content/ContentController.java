package com.academicregister.infraestucture.content;

import com.academicregister.application.content.IContentService;
import com.academicregister.domain.content.Content;
import com.academicregister.infraestucture.content.create.ContentCreateRequest;
import com.academicregister.infraestucture.content.create.ContentCreateResponse;
import io.swagger.annotations.ApiParam;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class ContentController {

    @Autowired
    private final IContentService contentService;

    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    public ContentController(IContentService contentService, ModelMapper modelMapper) {
        this.contentService = contentService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/content")
    public ResponseEntity<ContentCreateResponse> createContent(
            @ApiParam(value = "Content to create", required = true)
            @RequestBody ContentCreateRequest request) {
        var contentCreatedId = contentService.createContent(request.getCourse(), request.getSubject()).getCourse();
        return new ResponseEntity<>(new ContentCreateResponse(contentCreatedId), HttpStatus.CREATED);
    }

    @GetMapping("/content")
    public ResponseEntity<List<String>> contentByCourse(@RequestParam("course") String course){
        var subjectList = contentService.findSubjectsByCourse(course);
        List<String> response = new ArrayList<String>();
        subjectList.stream().forEach(s -> response.add(s.getTitle()));
       return new ResponseEntity<List<String>>(response, HttpStatus.OK);

    }
}
