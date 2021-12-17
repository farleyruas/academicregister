package com.academicregister.application.student;

import com.academicregister.domain.student.Student;
import com.academicregister.domain.student.IStudentRepository;
import com.academicregister.shared.exception.student.StudentEmailAlreadyExistsException;
import com.academicregister.shared.exception.student.StudentIdAlreadyExistsException;
import io.vavr.control.Option;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService{

    private final IStudentRepository studentRepository;

    StudentServiceImpl(IStudentRepository repository){
        this.studentRepository = repository;
    }

    @Override
    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    @Override
    public Student createStudent(Student student) {
        Option<Student> opStudentId = Option.of(studentRepository.findById(student.getId()));
        opStudentId.exists( rs -> {
           throw new StudentIdAlreadyExistsException(student.getId());
        });

        Option<Student> opStudentEmail = Option.of(studentRepository.findByEmail(student.getId()));
        opStudentEmail.exists( rs -> {
            throw new StudentEmailAlreadyExistsException(student.getEmail());
        });

        studentRepository.save(student);
        return student;
    }
}
