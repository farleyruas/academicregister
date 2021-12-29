package com.academicregister.application.resource;

import com.academicregister.domain.resource.Resource;

import java.util.List;

public interface IResourceService {
    String createResource(Resource resource);
    List<Resource> findAllResources();
}
