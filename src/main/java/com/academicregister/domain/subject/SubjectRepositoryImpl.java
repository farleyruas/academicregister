package com.academicregister.domain.subject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SubjectRepositoryImpl implements ISubjectRepository {

    private JdbcTemplate template;

    public SubjectRepositoryImpl(JdbcTemplate template){
        this.template = template;
    }

    @Override
    public void save(Subject subject) {
        template.update("INSERT INTO SUBJECTS VALUES (?, ?)",
                subject.getId(), subject.getTitle());
    }

    @Override
    public Subject findByTitle(String title) {
        String query = "SELECT * FROM SUBJECTS WHERE TITLE = ?";
        var result = template.query(query, new SubjectMapper(), title);
        if (!result.isEmpty()) {
            result.get(0);
        }
        return null;
    }
}
