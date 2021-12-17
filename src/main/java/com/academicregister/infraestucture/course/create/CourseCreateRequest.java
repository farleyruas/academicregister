package com.academicregister.infraestucture.course.create;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@ApiModel(value = "CourseCreateRequest")
public class CourseCreateRequest {
    @ApiModelProperty(value = "Student identifier", required = true)
    private String id;
    private String name;
    private String description;
}
