package com.academicregister.application.user;

import com.academicregister.domain.user.IUserRepository;
import com.academicregister.domain.user.User;
import com.academicregister.shared.exception.user.UserAlreadyExistsException;
import io.vavr.control.Option;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService{

    private final IUserRepository userRepository;

    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String createUser(User user) {
        Option<User> opUser = Option.of(userRepository.findById(user.getId()));
        opUser.exists( rs -> {
            throw new UserAlreadyExistsException(user.getId());
        });
        userRepository.save(user);
        return user.getId();
    }
}
