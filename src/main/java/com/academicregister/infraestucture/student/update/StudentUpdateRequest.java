package com.academicregister.infraestucture.student.update;

import io.swagger.annotations.ApiModel;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
@ApiModel(value = "StudentUpdateRequest")
public class StudentUpdateRequest {
    private String name;
    private String lastName;
}
