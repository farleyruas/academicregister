package com.academicregister.infraestucture.content;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
@ApiModel(value = "Content By Course Response")
public class ContentByCourseResponse {
    private String subject;
}
