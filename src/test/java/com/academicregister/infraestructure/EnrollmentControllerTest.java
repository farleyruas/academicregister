package com.academicregister.infraestructure;

import com.academicregister.application.enrollment.IEnrollmentService;
import com.academicregister.domain.enrollment.Enrollment;
import com.academicregister.infraestucture.enrollment.create.EnrollmentCreateRequest;
import com.academicregister.infraestucture.student.StudentController;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
public class EnrollmentControllerTest {

    @MockBean
    IEnrollmentService enrollmentService;

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void Create_Enrollment_Test() throws Exception {
        var request = new EnrollmentCreateRequest();
        request.setCourse("Math");
        request.setStudent("1035862325");

        var enrollment = new Enrollment();
        enrollment.setCourse(request.getCourse());
        enrollment.setStudent(request.getStudent());

        //when(enrollmentService.createEnrollment(any(Enrollment.class))).thenReturn(enrollment);

        var result = mockMvc.perform(post("/enrollments")
                .content(mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isCreated());
    }

}
