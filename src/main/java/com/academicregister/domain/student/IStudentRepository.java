package com.academicregister.domain.student;

import java.util.List;

public interface IStudentRepository{
    Student save(Student student);
    List<Student> findAll();
    Student findById(String id);
    Student findByEmail(String email);
}
