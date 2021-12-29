package com.academicregister.infraestucture.resource;

import com.academicregister.application.resource.IResourceService;
import com.academicregister.domain.resource.Resource;
import com.academicregister.infraestucture.resource.create.ResourceCreateRequest;
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
public class ResourceController {

    @Autowired
    private final IResourceService resourceService;

    @Autowired
    private final ModelMapper modelMapper;


    public ResourceController(IResourceService resourceService, ModelMapper modelMapper) {
        this.resourceService = resourceService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/resource")
    public ResponseEntity<String> createResource(
            @ApiParam(value = "Resource to create", required = true)
            @RequestBody ResourceCreateRequest request) {

        var resource = Resource.builder()
                .id(UUID.randomUUID().toString())
                .url(request.getUrl())
                .build();

        var resourceCreatedId = resourceService.createResource(resource);
        return new ResponseEntity<>(resourceCreatedId, HttpStatus.CREATED);
    }

}
