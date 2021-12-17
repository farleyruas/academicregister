package com.academicregister.application;

import com.academicregister.application.student.StudentServiceImpl;
import com.academicregister.domain.student.IStudentRepository;
import com.academicregister.domain.student.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {

    @Mock
    IStudentRepository studentRepository;

    @InjectMocks
    StudentServiceImpl studentService;

    @Captor
    private ArgumentCaptor<String> studentArgumentCaptor;

    @Test
    public void Create_Student_Test(){
        var student = new Student();
        student.setId("1035862325");
        student.setName("Farley");
        student.setLastName("Rua Suarez");

        //when(studentRepository.save(student)).thenReturn(Optional.empty());
        //var studentCreated = studentService.createStudent(student);
        //assertThat(studentCreated.getId()).isSameAs(student.getId());
    }

    @Test
    public void Get_Students_Test(){
        when(studentRepository.findAll()).thenReturn(new ArrayList<Student>());
        var result = studentService.getStudents();
        assertThat(result).isNotNull();
    }

}
