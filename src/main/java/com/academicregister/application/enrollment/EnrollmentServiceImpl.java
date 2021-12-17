package com.academicregister.application.enrollment;

import com.academicregister.domain.course.ICourseRepository;
import com.academicregister.domain.enrollment.Enrollment;
import com.academicregister.domain.enrollment.IEnrollmentRepository;
import com.academicregister.domain.student.IStudentRepository;
import com.academicregister.shared.exception.course.CourseNotFoundException;
import com.academicregister.shared.exception.student.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentServiceImpl implements IEnrollmentService{

    private final IEnrollmentRepository enrollmentRepository;
    private final ICourseRepository courseRepository;
    private final IStudentRepository studentRepository;

    EnrollmentServiceImpl(IEnrollmentRepository enrollmentRepository, ICourseRepository courseRepository,
                          IStudentRepository studentRepository){
        this.enrollmentRepository = enrollmentRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Enrollment> getEnrollments() {
        return null;
    }

    @Override
    public List<Enrollment> getEnrollmentsByStudent() {
        return null;
    }

    @Override
    public List<Enrollment> getEnrollmentsByCourse() {
        return null;
    }

    @Override
    public String createEnrollment(Enrollment enrollment) {
        var optionalStudent = studentRepository.findById(enrollment.getStudentId());
        var optionalCourse = courseRepository.findById(enrollment.getCourseId());

        if (!optionalStudent.isPresent()) {
            throw new StudentNotFoundException(enrollment.getStudentId());
        }

        if (!optionalCourse.isPresent()) {
            throw new CourseNotFoundException(enrollment.getCourseId());
        }

        enrollmentRepository.save(enrollment);
        return enrollment.getId();
    }

    @Override
    public void deleteEnrollmentByStudent(String studentId) {

    }

    @Override
    public void updateEnrollment(Enrollment enrollment) {

    }
}
