package com.academicregister.shared.exception;

import com.academicregister.shared.exception.course.CourseAlreadyExistsException;
import com.academicregister.shared.exception.course.CourseNotFoundException;
import com.academicregister.shared.exception.enrollment.EnrollmentAlreadyExistsException;
import com.academicregister.shared.exception.student.StudentIdAlreadyExistsException;
import com.academicregister.shared.exception.student.StudentEmailAlreadyExistsException;
import com.academicregister.shared.exception.student.StudentNotFoundException;
import com.academicregister.shared.exception.subject.SubjectAlreadyExistsException;
import com.academicregister.shared.exception.user.NotValidLoginException;
import com.academicregister.shared.exception.user.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({StudentIdAlreadyExistsException.class,
            StudentEmailAlreadyExistsException.class,
            CourseAlreadyExistsException.class,
            EnrollmentAlreadyExistsException.class,
            SubjectAlreadyExistsException.class})
    public ResponseEntity<ErrorResponse> handleAlreadyExists(Exception ex, WebRequest request) {

        ErrorResponse errors = new ErrorResponse();
        errors.setError(ex.getMessage());
        errors.setStatus(HttpStatus.CONFLICT.value());

        return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({StudentNotFoundException.class,
            CourseNotFoundException.class,
            NotValidLoginException.class,
            UserAlreadyExistsException.class})
    public ResponseEntity<ErrorResponse> handleNotFound(Exception ex, WebRequest request) {

        ErrorResponse errors = new ErrorResponse();
        errors.setError(ex.getMessage());
        errors.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(errors, HttpStatus.CONFLICT);

    }
}
