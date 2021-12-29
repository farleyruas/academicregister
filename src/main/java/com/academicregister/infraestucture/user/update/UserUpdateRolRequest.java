package com.academicregister.infraestucture.user.update;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@ApiModel(value = "UserUpdateRolRequest")
public class UserUpdateRolRequest {
    public String username;
    public String role;
}
