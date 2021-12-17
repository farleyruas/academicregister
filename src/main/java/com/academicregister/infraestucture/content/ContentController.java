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

    @PostMapping("/contents")
    public ResponseEntity<ContentCreateResponse> createContent(
            @ApiParam(value = "Content to create", required = true)
            @RequestBody ContentCreateRequest request) {
        var content = modelMapper.map(request, Content.class);
        content.setId(UUID.randomUUID().toString());
        var contentCreatedId = contentService.createContent(content).getId();
        return new ResponseEntity<>(new ContentCreateResponse(contentCreatedId), HttpStatus.CREATED);
    }
}
