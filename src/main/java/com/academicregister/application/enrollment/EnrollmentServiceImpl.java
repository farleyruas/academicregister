package com.academicregister.application.enrollment;

import com.academicregister.domain.course.Course;
import com.academicregister.domain.course.ICourseRepository;
import com.academicregister.domain.enrollment.Enrollment;
import com.academicregister.domain.enrollment.IEnrollmentRepository;
import com.academicregister.domain.student.IStudentRepository;
import com.academicregister.domain.student.Student;
import com.academicregister.shared.exception.course.CourseNotFoundException;
import com.academicregister.shared.exception.enrollment.EnrollmentAlreadyExistsException;
import com.academicregister.shared.exception.student.StudentNotFoundException;
import io.vavr.control.Option;
import io.vavr.control.Try;
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
    public List<Enrollment> getEnrollmentsByStudent() {
        return null;
    }

    @Override
    public List<Enrollment> getEnrollmentsByCourse() {
        return null;
    }

    @Override
    public String createEnrollment(Enrollment enrollment) {

        Option<Enrollment> opEnrollment = Option.of(enrollmentRepository.findByCourseAndStudent(
                enrollment.getCourseId(), enrollment.getStudentId()));

        opEnrollment.exists( rs -> {
            throw new EnrollmentAlreadyExistsException(enrollment.getCourseId(), enrollment.getStudentId());
        });

        Try<Student> rsStudent = Try.of(() -> studentRepository.findById(enrollment.getStudentId()));
        rsStudent.getOrElseThrow(throwable -> {
            throw new StudentNotFoundException(enrollment.getStudentId());
        });

        Try<Course> rsCourse = Try.of(() -> courseRepository.findById(enrollment.getCourseId()));
        rsCourse.getOrElseThrow(throwable -> {
            throw new CourseNotFoundException(enrollment.getCourseId());
        });

        enrollmentRepository.save(enrollment);
        return enrollment.getId();
    }


}
