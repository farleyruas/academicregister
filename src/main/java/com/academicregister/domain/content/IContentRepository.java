package com.academicregister.domain.content;

import java.util.List;

public interface IContentRepository {
    void save(Content content);
    List<Content> getContentsByCourse(String courseId);
}
