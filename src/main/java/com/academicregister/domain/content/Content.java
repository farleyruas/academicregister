package com.academicregister.domain.content;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class Content {
    private @Id String id;
    private String courseId;
    private String subjectId;
}
