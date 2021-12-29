package com.academicregister.domain.resource;

import java.util.List;

public interface IResourceRepository {
    void save(Resource resource);
    Resource findById(String id);
    Resource findByUri(String url);
    List<Resource> findAll();
}
