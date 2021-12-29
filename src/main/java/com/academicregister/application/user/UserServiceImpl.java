package com.academicregister.application.user;

import com.academicregister.domain.resource.Resource;
import com.academicregister.domain.rol.IRoleRepository;
import com.academicregister.domain.rol.Role;
import com.academicregister.domain.user.IUserRepository;
import com.academicregister.domain.user.User;
import com.academicregister.shared.exception.resource.ResourceNotFoundException;
import com.academicregister.shared.exception.role.RoleNotFoundException;
import com.academicregister.shared.exception.user.UserAlreadyExistsException;
import com.academicregister.shared.exception.user.UserNotFoundException;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService{

    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;

    public UserServiceImpl(IUserRepository userRepository, IRoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public String createUser(User user) {
        Try<Role> rsRole = Try.of(() -> roleRepository.findByName(user.getRole()));
        rsRole.getOrElseThrow(throwable -> {
            throw new RoleNotFoundException(user.getRole());
        });

        Option<User> opUserById = Option.of(userRepository.findById(user.getId()));
        opUserById.exists( rs -> {
            throw new UserAlreadyExistsException(user.getId());
        });

        Option<User> opUserByUsername = Option.of(userRepository.findByUsername(user.getUsername()));
        opUserByUsername.exists( rs -> {
            throw new UserAlreadyExistsException(user.getUsername());
        });

        user.setRole(rsRole.get().getId());
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public void updateRolUser(User user) {
        Try<Role> rsRole = Try.of(() -> roleRepository.findByName(user.getRole()));
        rsRole.getOrElseThrow(throwable -> {
            throw new RoleNotFoundException(user.getRole());
        });

        Try<User> rsUser = Try.of(() -> userRepository.findByUsername(user.getUsername()));
        rsUser.getOrElseThrow(throwable -> {
            throw new UserNotFoundException(user.getUsername());
        });

        rsUser.get().setRole(rsRole.get().getId());
        userRepository.delete(rsUser.get().getId());
        userRepository.save(rsUser.get());
    }
}
