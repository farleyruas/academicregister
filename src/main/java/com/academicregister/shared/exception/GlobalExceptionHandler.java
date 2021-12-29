package com.academicregister.shared.exception;

import com.academicregister.shared.exception.course.CourseAlreadyExistsException;
import com.academicregister.shared.exception.course.CourseNotFoundException;
import com.academicregister.shared.exception.enrollment.EnrollmentAlreadyExistsException;
import com.academicregister.shared.exception.permission.PermissionAlreadyExistsException;
import com.academicregister.shared.exception.resource.ResourceAlreadyExistsException;
import com.academicregister.shared.exception.role.RoleAlreadyExistsException;
import com.academicregister.shared.exception.role.RoleNotFoundException;
import com.academicregister.shared.exception.student.StudentIdAlreadyExistsException;
import com.academicregister.shared.exception.student.StudentEmailAlreadyExistsException;
import com.academicregister.shared.exception.student.StudentNotFoundException;
import com.academicregister.shared.exception.subject.SubjectAlreadyExistsException;
import com.academicregister.shared.exception.user.NotValidLoginException;
import com.academicregister.shared.exception.user.UserAlreadyExistsException;
import com.academicregister.shared.exception.user.UserNotFoundException;
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
            SubjectAlreadyExistsException.class,
            RoleAlreadyExistsException.class,
            ResourceAlreadyExistsException.class,
            UserAlreadyExistsException.class,
            PermissionAlreadyExistsException.class})
    public ResponseEntity<ErrorResponse> handleAlreadyExists(Exception ex, WebRequest request) {

        var errors = ErrorResponse.builder()
                .error(ex.getMessage())
                .status(HttpStatus.CONFLICT.value())
                .build();

        return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({StudentNotFoundException.class,
            CourseNotFoundException.class,
            UserNotFoundException.class,
            NotValidLoginException.class,
            RoleNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleNotFound(Exception ex, WebRequest request) {

        var errors = ErrorResponse.builder()
                .error(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .build();

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);

    }
}
