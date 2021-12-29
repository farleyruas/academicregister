package com.academicregister.domain.student;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public class StudentRepositoryImpl implements IStudentRepository {

    private final JdbcTemplate template;

    public StudentRepositoryImpl(JdbcTemplate template){
        this.template = template;
    }

    @Transactional
    public Student save(Student student){
        template.update("INSERT INTO STUDENTS VALUES (?, ?, ?, ?)",
                student.getId(), student.getName(), student.getLastName(), student.getEmail());
        return findById(student.getId());
    }

    public List<Student> findAll(){
        return template.query("SELECT * FROM STUDENTS", new StudentMapper());
    }

    public Student findById(String id){
        var query = "SELECT * FROM STUDENTS WHERE ID = ?";
        var result = template.query(query, new StudentMapper(), id);
        return result.get(0);
    }

    @Override
    public Student findByEmail(String email) {
        var query = "SELECT * FROM STUDENTS WHERE EMAIL = ?";
        var result = template.query(query, new StudentMapper(), email);
        return result.get(0);
    }
}
