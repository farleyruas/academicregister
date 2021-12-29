package com.academicregister.shared.exception.content;

public class ContentAlreadyExistsException extends RuntimeException {
    public ContentAlreadyExistsException(String courseId, String subjectId) {
        super(String.format("Contente with course id <%s> and subject id <%s> already exists.", courseId, subjectId));
    }
}
