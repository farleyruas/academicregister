package com.academicregister.domain.content;

import com.academicregister.domain.subject.Subject;

import java.util.List;

public interface IContentRepository {
    void save(Content content);
    Content findByCourseAndSubject(String courseId, String subjectrId);
    List<Content> findByCourse(String course);
}
