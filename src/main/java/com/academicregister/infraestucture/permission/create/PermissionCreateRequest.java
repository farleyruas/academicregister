package com.academicregister.infraestucture.permission.create;

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
@ApiModel(value = "PermissionCreateRequest")
public class PermissionCreateRequest {
    private String role;
    private String resource;
}
