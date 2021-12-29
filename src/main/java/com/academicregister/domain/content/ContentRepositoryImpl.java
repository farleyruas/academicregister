package com.academicregister.domain.content;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ContentRepositoryImpl implements IContentRepository{

    private JdbcTemplate template;

    public ContentRepositoryImpl(JdbcTemplate template){
        this.template = template;
    }

    @Transactional
    public void save(Content content) {
        template.update("INSERT INTO CONTENTS VALUES (?, ?, ?)",
                content.getCourseId(), content.getSubjectId(), content.getId());
    }

    @Override
    public Content findByCourseAndSubject(String courseId, String subjectId) {
        String query = "SELECT * FROM CONTENTS WHERE COURSE = ? AND SUBJECT = ?";
        var result = template.query(query, new ContentMapper(), courseId, subjectId);
        if (!result.isEmpty()) {
            result.get(0);
        }
        return null;
    }
}
