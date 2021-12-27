package com.academicregister.infraestucture.user.create;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ApiModel(value = "UserCreateResponse")
public class UserCreateResponse {
    @ApiModelProperty(value = "User identifier")
    private final String id;
}
