package com.academicregister.infraestucture.student.create;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@ApiModel(value = "StudentCreateResponse")
public class StudentCreateResponse {
    @ApiModelProperty(value = "Student identifier")
    private final String id;
}
