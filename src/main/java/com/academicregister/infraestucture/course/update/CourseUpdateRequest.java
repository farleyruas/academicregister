package com.academicregister.infraestucture.course.update;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@ApiModel(value = "CourseUpdateRequest")
public class CourseUpdateRequest {
    private String name;
    private String description;
}
