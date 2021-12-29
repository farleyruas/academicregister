package com.academicregister.infraestucture.user.create;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
@ApiModel(value = "UserCreateRequest")
public class UserCreateRequest {
    private String id;
    private String username;
    private String password;
    private String role;
}
