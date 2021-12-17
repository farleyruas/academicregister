package com.academicregister.infraestucture.enrollment.create;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ApiModel(value = "EnrollmentCreateResponse")
public class EnrollmentCreateResponse {
    @ApiModelProperty(value = "Enrollment identifier")
    private final String id;
}
