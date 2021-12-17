package com.academicregister.domain;

import com.academicregister.domain.student.IStudentRepository;
import com.academicregister.domain.student.StudentRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class StudentRepositoryTest {

    private IStudentRepository studentRepository;

    private JdbcTemplate template;

    @Autowired
    public StudentRepositoryTest(JdbcTemplate template) {
        this.template = template;
        studentRepository = new StudentRepositoryImpl(template);
    }

    @Test
    void FindByIdTest(){
        var result = studentRepository.findById("001");
        assertNotNull(result);
        assertThat(result.get().getId()).isEqualTo("001");
    }
}
