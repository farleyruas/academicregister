package com.academicregister.domain.permission;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PermissionRepositoryImpl implements IPermissionRepository{

    private final JdbcTemplate template;

    public PermissionRepositoryImpl(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public void save(Permission permission) {
        template.update("INSERT INTO PERMISSIONS VALUES (?, ?)", permission.getRole(), permission.getResource());
    }

    @Override
    public List<Permission> findByRole(String role) {
        var query = "SELECT * FROM PERMISSIONS WHERE ROLE = ?";
        var result = template.query(query, new PermissionMapper(), role);
        if (!result.isEmpty()) {
            return result;
        }
        return null;
    }

    @Override
    public List<Permission> findByResource(String resource) {
        var query = "SELECT * FROM PERMISSIONS WHERE RESOURCE = ?";
        var result = template.query(query, new PermissionMapper(), resource);
        if (!result.isEmpty()) {
            return result;
        }
        return null;
    }

    @Override
    public Permission findByRoleAndResource(String roleId, String resourceId) {
        var query = "SELECT * FROM PERMISSIONS WHERE ROLE = ? AND RESOURCE = ?";
        var result = template.query(query, new PermissionMapper(), roleId, resourceId);
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }
}
