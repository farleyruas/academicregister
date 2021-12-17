package com.academicregister.domain.student;

import java.util.List;
import java.util.Optional;

public interface IStudentRepository{
    Optional<Student> save(Student student);
    List<Student> findAll();
    Optional<Student> findById(String id);
    Optional<Student> findByEmail(String email);
}
