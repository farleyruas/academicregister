package com.academicregister.infraestucture.subject.create;

import io.swagger.annotations.ApiModel;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@ApiModel(value = "SubjectCreateRequest")
public class SubjectCreateRequest {

    private String title;
}
