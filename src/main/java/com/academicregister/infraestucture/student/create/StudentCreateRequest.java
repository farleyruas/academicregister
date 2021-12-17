package com.academicregister.infraestucture.student.create;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
@ApiModel(value = "StudentCreateRequest")
public class StudentCreateRequest {
    @ApiModelProperty(value = "Student identifier", required = true)
    private String id;
    private String name;
    private String lastName;
    private String email;
}
