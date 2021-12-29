package com.academicregister.infraestucture.content.create;

import io.swagger.annotations.ApiModel;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
@ApiModel(value = "Content Create Request")
public class ContentCreateRequest {
    private String course;
    private String subject;
}
