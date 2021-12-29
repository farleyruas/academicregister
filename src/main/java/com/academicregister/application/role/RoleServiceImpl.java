package com.academicregister.application.role;

import com.academicregister.domain.rol.IRoleRepository;
import com.academicregister.domain.rol.Role;
import com.academicregister.shared.exception.role.RoleAlreadyExistsException;
import io.vavr.control.Option;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService{

    private final IRoleRepository roleRepository;

    public RoleServiceImpl(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public String createRole(Role role) {
        Option<Role> opRoleById = Option.of(roleRepository.findById(role.getId()));
        opRoleById.exists(rs -> {
            throw new RoleAlreadyExistsException(role.getId());
        });

        Option<Role> opRoleByName = Option.of(roleRepository.findByName(role.getName()));
        opRoleByName.exists(rs -> {
            throw new RoleAlreadyExistsException(role.getName());
        });
        roleRepository.save(role);
        return role.getId();
    }

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }
}
