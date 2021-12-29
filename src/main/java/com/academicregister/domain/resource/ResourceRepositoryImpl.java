package com.academicregister.domain.resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResourceRepositoryImpl implements IResourceRepository{

    private final JdbcTemplate template;

    public ResourceRepositoryImpl(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public void save(Resource resource) {
        template.update("INSERT INTO RESOURCES VALUES (?, ?)", resource.getId(), resource.getUrl());

    }

    @Override
    public Resource findById(String id) {
        var query = "SELECT * FROM RESOURCES WHERE ID = ?";
        var result = template.query(query, new ResourceMapper(), id);
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    @Override
    public Resource findByUri(String url) {
        var query = "SELECT * FROM RESOURCES WHERE URL = ?";
        var result = template.query(query, new ResourceMapper(), url);
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    @Override
    public List<Resource> findAll() {
        var query = "SELECT * FROM RESOURCES";
        var result = template.query(query, new ResourceMapper());
        if (!result.isEmpty()) {
            return result;
        }
        return null;
    }
}
