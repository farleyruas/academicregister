package com.academicregister.application.student;

import com.academicregister.domain.student.Student;

import java.util.List;

public interface IStudentService {
    List<Student> getStudents();
    Student createStudent(Student student);
}
