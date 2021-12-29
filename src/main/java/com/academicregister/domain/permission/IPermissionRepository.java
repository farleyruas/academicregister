package com.academicregister.domain.permission;

import java.util.List;

public interface IPermissionRepository {
    void save(Permission permission);
    List<Permission> findByRole(String role);
    List<Permission> findByResource(String resource);
    Permission findByRoleAndResource(String role, String resource);
}
