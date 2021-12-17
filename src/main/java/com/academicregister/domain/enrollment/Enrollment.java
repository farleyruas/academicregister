package com.academicregister.domain.enrollment;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
@Entity
public class Enrollment {
    private @Id String id;
    private String studentId;
    private String courseId;
}
