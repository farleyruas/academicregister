package com.academicregister.domain.student;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepositoryImpl implements IStudentRepository {

    private JdbcTemplate template;

    public StudentRepositoryImpl(JdbcTemplate template){
        this.template = template;
    }

    @Transactional
    public Optional<Student> save(Student student){
        template.update("INSERT INTO STUDENTS VALUES (?, ?, ?, ?)",
                student.getId(), student.getName(), student.getLastName(), student.getEmail());
        return findById(student.getId());
    }

    public List<Student> findAll(){
        return template.query("SELECT * FROM STUDENTS", new StudentMapper());
    }

    public Optional<Student> findById(String id){
        String query = "SELECT * FROM STUDENTS WHERE ID = ?";
        var result = template.query(query, new StudentMapper(), id);
        if (result.isEmpty()){
            return Optional.empty();
        }
        return Optional.ofNullable(result.get(0));
    }

    @Override
    public Optional<Student> findByEmail(String email) {
        String query = "SELECT * FROM STUDENTS WHERE EMAIL = ?";
        var result = template.query(query, new StudentMapper(), email);
        if (result.isEmpty()){
            return Optional.empty();
        }
        return Optional.ofNullable(result.get(0));
    }
}
