package com.academicregister.domain.content;

import com.academicregister.domain.subject.Subject;
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
                content.getCourse(), content.getSubject());
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

    @Override
    public List<Content> findByCourse(String course) {
        String query = "SELECT * FROM CONTENTS WHERE COURSE = ?";
        var result = template.query(query, new ContentMapper(), course);
        if (!result.isEmpty()) {
            return result;
        }
        return null;
    }
}
