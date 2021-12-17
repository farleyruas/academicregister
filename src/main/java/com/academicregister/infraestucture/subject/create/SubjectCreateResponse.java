package com.academicregister.infraestucture.subject.create;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ApiModel(value = "SubjectCreateResponse")
public class SubjectCreateResponse {
    @ApiModelProperty(value = "Subject identifier")
    private final String id;
}