package com.academicregister.infraestructure;

import com.academicregister.application.student.IStudentService;
import com.academicregister.domain.student.Student;
import com.academicregister.infraestucture.student.StudentController;
import com.academicregister.infraestucture.student.create.StudentCreateRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @MockBean
    IStudentService studentService;

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void Create_Student_Test() throws Exception {
        var request = new StudentCreateRequest();
        request.setId("1035862325");
        request.setName("Farley");
        request.setLastName("Rúa Suárez");

        var student = new Student();
        student.setId(request.getId());
        student.setName(request.getName());
        student.setLastName(request.getLastName());

        when(studentService.createStudent(any(Student.class))).thenReturn(student);

        var result = mockMvc.perform(post("/students")
                .content(mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("id").exists());
    }

    @Test
    public void Get_Students_Test() throws Exception {
        var result = mockMvc.perform(get("/students"));
        result.andExpect(status().isOk());
        result.andReturn();
    }

}
