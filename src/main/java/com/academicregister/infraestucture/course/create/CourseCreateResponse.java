package com.academicregister.infraestucture.course.create;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@ApiModel(value = "CourseCreateResponse")
public class CourseCreateResponse {
    @ApiModelProperty(value = "Course identifier")
    private final String id;
}
