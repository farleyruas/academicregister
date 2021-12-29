package com.academicregister.infraestucture.content.create;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@ApiModel(value = "Content Create Response")
public class ContentCreateResponse{
    @ApiModelProperty(value = "Content identifier")
    private final String id;
}


