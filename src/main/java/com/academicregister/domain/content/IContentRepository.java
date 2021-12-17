package com.academicregister.domain.content;

import java.util.List;

public interface IContentRepository {
    void save(Content content);
    Content findByCourseAndSubject(String courseId, String subjectrId);
}
