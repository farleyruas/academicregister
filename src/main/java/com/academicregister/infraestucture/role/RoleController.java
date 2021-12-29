package com.academicregister.infraestucture.role;

import com.academicregister.application.role.IRoleService;
import com.academicregister.domain.rol.Role;
import com.academicregister.infraestucture.role.create.RoleCreateRequest;
import com.academicregister.infraestucture.subject.create.SubjectCreateRequest;
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
public class RoleController {

    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    private final IRoleService roleService;

    public RoleController(ModelMapper modelMapper, IRoleService roleService) {
        this.modelMapper = modelMapper;
        this.roleService = roleService;
    }

    @PostMapping("/role")
    public ResponseEntity<String> createRole(
            @ApiParam(value = "Role to create", required = true)
            @RequestBody RoleCreateRequest request) {
        var role = Role.builder()
                .id(UUID.randomUUID().toString())
                .name(request.getName())
                .build();
        var roleCreatedId = roleService.createRole(role);
        return new ResponseEntity<>(roleCreatedId, HttpStatus.CREATED);
    }
}
