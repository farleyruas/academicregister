package com.academicregister.domain.subject;

public interface ISubjectRepository {
    void save(Subject subject);
    Subject findByTitle(String title);
    Subject findById(String id);
}