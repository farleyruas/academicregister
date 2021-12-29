package com.academicregister.infraestucture.enrollment.create;

import io.swagger.annotations.ApiModel;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
@ApiModel(value = "EnrollmentCreateRequest")
public class EnrollmentCreateRequest {
    private String student;
    private String course;
}
