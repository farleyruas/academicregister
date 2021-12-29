package com.academicregister.application.permission;

import com.academicregister.domain.permission.IPermissionRepository;
import com.academicregister.domain.permission.Permission;
import com.academicregister.domain.resource.IResourceRepository;
import com.academicregister.domain.resource.Resource;
import com.academicregister.domain.rol.IRoleRepository;
import com.academicregister.domain.rol.Role;

import com.academicregister.shared.exception.permission.PermissionAlreadyExistsException;
import com.academicregister.shared.exception.resource.ResourceNotFoundException;
import com.academicregister.shared.exception.role.RoleNotFoundException;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements IPermissionService{

    private final IPermissionRepository permissionRepository;
    private final IRoleRepository roleRepository;
    private final IResourceRepository resourceRepository;

    public PermissionServiceImpl(IPermissionRepository permissionRepository, IRoleRepository roleRepository, IResourceRepository resourceRepository) {
        this.permissionRepository = permissionRepository;
        this.roleRepository = roleRepository;
        this.resourceRepository = resourceRepository;
    }

    @Override
    public void createPermission(String role, String resource) {
        Try<Role> rsRole = Try.of(() -> roleRepository.findByName(role));
        rsRole.getOrElseThrow(throwable -> {
            throw new RoleNotFoundException(role);
        });

        Try<Resource> rsResource = Try.of(() -> resourceRepository.findByUri(resource));
        rsResource.getOrElseThrow(throwable -> {
            throw new ResourceNotFoundException(resource);
        });

        Option<Permission> opPermission = Option.of(permissionRepository.findByRoleAndResource(rsRole.get().getId(),
                rsResource.get().getId()));
        opPermission.exists( rs -> {
            throw new PermissionAlreadyExistsException(role, resource);
        });

        var permission = Permission.builder()
            .role(rsRole.get().getId())
            .resource(rsResource.get().getId())
            .build();

        permissionRepository.save(permission);
    }

}
