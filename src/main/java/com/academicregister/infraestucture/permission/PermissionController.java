package com.academicregister.infraestucture.permission;

import com.academicregister.application.permission.IPermissionService;
import com.academicregister.infraestucture.permission.create.PermissionCreateRequest;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PermissionController {

    @Autowired
    private final IPermissionService permissionService;

    public PermissionController(IPermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping("/permission")
    public ResponseEntity<Void> createPermission(
            @ApiParam(value = "Permission to create", required = true)
            @RequestBody PermissionCreateRequest request) {
        permissionService.createPermission(request.getRole(), request.getResource());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
