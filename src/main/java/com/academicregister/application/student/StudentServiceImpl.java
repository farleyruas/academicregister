package com.academicregister.application.student;

import com.academicregister.domain.student.Student;
import com.academicregister.domain.student.IStudentRepository;
import com.academicregister.shared.exception.student.StudentIdAlreadyExistsException;
import com.academicregister.shared.exception.student.StudentEmailAlreadyExistsException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService{

    private final IStudentRepository repository;

    StudentServiceImpl(IStudentRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Student> getStudents(){
        return repository.findAll();
    }

    @Override
    public Student createStudent(Student student) {
        var optionalStudentById = repository.findById(student.getId());
        var optionalStudentByEmail = repository.findByEmail(student.getEmail());

        optionalStudentById.ifPresent(st -> { throw new StudentIdAlreadyExistsException(student.getId()); });
        optionalStudentByEmail.ifPresent(st -> { throw new StudentEmailAlreadyExistsException(student.getEmail()); });

        repository.save(student);
        return student;
    }
}
