package com.academicregister.application.content;

import com.academicregister.domain.content.Content;
import com.academicregister.domain.subject.Subject;

import java.util.List;

public interface IContentService {
    Content createContent(String course, String subject);
    List<Subject> findSubjectsByCourse(String course);
}
