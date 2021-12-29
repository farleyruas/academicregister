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

import java.util.ArrayList;
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
    public List<Course> getEnrollmentsByStudent(String studentId) {
        Try<Student> rsStudent = Try.of(() -> studentRepository.findById(studentId));
        rsStudent.getOrElseThrow(throwable -> {
            throw new StudentNotFoundException(studentId);
        });
        List<Course> courseList = new ArrayList<Course>();
        enrollmentRepository.findByStudent(studentId).stream().forEach(e -> courseList.add(courseRepository.findById(e.getCourse())));
        return courseList;
    }

    @Override
    public List<Enrollment> getEnrollmentsByCourse(String course) {
        return null;
    }

    @Override
    public void createEnrollment(Enrollment enrollment) {

        Try<Student> rsStudent = Try.of(() -> studentRepository.findById(enrollment.getStudent()));
        rsStudent.getOrElseThrow(throwable -> {
            throw new StudentNotFoundException(enrollment.getStudent());
        });

        Try<Course> rsCourse = Try.of(() -> courseRepository.findByName(enrollment.getCourse()));
        rsCourse.getOrElseThrow(throwable -> {
            throw new CourseNotFoundException(enrollment.getCourse());
        });

        Option<Enrollment> opEnrollment = Option.of(enrollmentRepository.findByCourseAndStudent(
                rsCourse.get().getId(), rsStudent.get().getId()));

        opEnrollment.exists( rs -> {
            throw new EnrollmentAlreadyExistsException(enrollment.getCourse(), enrollment.getStudent());
        });

        enrollmentRepository.save(Enrollment.builder()
                .course(rsCourse.get().getId())
                .student(rsStudent.get().getId())
                .build());
    }


}
