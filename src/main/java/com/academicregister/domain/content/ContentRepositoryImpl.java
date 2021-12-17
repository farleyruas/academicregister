package com.academicregister.domain.content;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ContentRepositoryImpl implements IContentRepository{

    private JdbcTemplate template;

    public ContentRepositoryImpl(JdbcTemplate template){
        this.template = template;
    }

    @Transactional
    public void save(Content content) {
        template.update("INSERT INTO CONTENTS VALUES (?, ?)",
                content.getCourseId(), content.getSubjectId());
    }

    @Override
    public List<Content> getContentsByCourse(String courseId) {
        String query = "SELECT * FROM CONTENTS WHERE COURSE = ?";
        var result = template.query(query, new ContentMapper(), courseId);
        return result;
    }
}
