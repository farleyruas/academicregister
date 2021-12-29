package com.academicregister.application.resource;

import com.academicregister.domain.resource.IResourceRepository;
import com.academicregister.domain.resource.Resource;
import com.academicregister.shared.exception.resource.ResourceAlreadyExistsException;
import io.vavr.control.Option;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl implements IResourceService{

    private final IResourceRepository resourceRepository;

    public ResourceServiceImpl(IResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    @Override
    public String createResource(Resource resource) {
        Option<Resource> opRoleById = Option.of(resourceRepository.findById(resource.getId()));
        opRoleById.exists( rs -> {
            throw new ResourceAlreadyExistsException(resource.getId());
        });

        Option<Resource> opRoleByUrl = Option.of(resourceRepository.findByUri(resource.getUrl()));
        opRoleByUrl.exists( rs -> {
            throw new ResourceAlreadyExistsException(resource.getUrl());
        });

        resourceRepository.save(resource);
        return resource.getId();
    }

    @Override
    public List<Resource> findAllResources() {
        return resourceRepository.findAll();
    }
}
