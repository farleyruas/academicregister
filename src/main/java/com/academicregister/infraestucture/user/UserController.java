package com.academicregister.infraestucture.user;

import com.academicregister.application.security.ISecurityService;
import com.academicregister.application.user.IUserService;
import com.academicregister.domain.user.User;
import com.academicregister.infraestucture.user.create.UserCreateRequest;
import com.academicregister.infraestucture.user.create.UserCreateResponse;
import com.academicregister.infraestucture.user.update.UserUpdateRolRequest;
import io.swagger.annotations.ApiParam;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
public class UserController {

    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    private final ISecurityService securityService;

    @Autowired
    private final IUserService userService;

    public UserController(ModelMapper modelMapper, ISecurityService securityService, IUserService userService) {
        this.modelMapper = modelMapper;
        this.securityService = securityService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String pwd) throws NoSuchAlgorithmException {
        var encryptedPassword = securityService.encryptText(pwd);
        if (securityService.isValidLogin(username, encryptedPassword)) {
            return securityService.getJWTToken(username);
        }
        return null;
    }

    @PostMapping("/user")
    public ResponseEntity<UserCreateResponse> createUser(
        @ApiParam(value = "User  to Create", required = true)
        @RequestBody UserCreateRequest userCreateRequest) {
        var pwd = securityService.encryptText(userCreateRequest.getPassword());
        userCreateRequest.setPassword(pwd);
        var user = modelMapper.map(userCreateRequest, User.class);
        var userCreatedId = userService.createUser(user);
        return new ResponseEntity<>(new UserCreateResponse(userCreatedId), HttpStatus.CREATED);
    }

    @PostMapping("/role-user")
    public ResponseEntity<Void> setRole(
            @ApiParam(value = "User  to Create", required = true)
            @RequestBody UserUpdateRolRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .role(request.getRole())
                .build();
        userService.updateRolUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
