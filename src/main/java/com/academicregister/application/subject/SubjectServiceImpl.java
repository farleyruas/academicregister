package com.academicregister.application.subject;

import com.academicregister.domain.subject.ISubjectRepository;
import com.academicregister.domain.subject.Subject;
import com.academicregister.shared.exception.subject.SubjectAlreadyExistsException;
import io.vavr.control.Option;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements ISubjectService{

    private final ISubjectRepository subjectRepository;

    public SubjectServiceImpl(ISubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public Subject createSubject(Subject subject) {
        Option<Subject> opSubject = Option.of(subjectRepository.findByTitle(subject.getTitle()));
        opSubject.exists( rs -> {
            throw new SubjectAlreadyExistsException(subject.getTitle());
        });

        subjectRepository.save(subject);
        return subject;
    }
}
