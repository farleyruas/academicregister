package com.academicregister.application.content;

import com.academicregister.domain.content.Content;
import com.academicregister.domain.content.IContentRepository;
import com.academicregister.domain.course.Course;
import com.academicregister.domain.course.ICourseRepository;
import com.academicregister.domain.student.Student;
import com.academicregister.domain.subject.ISubjectRepository;
import com.academicregister.domain.subject.Subject;
import com.academicregister.shared.exception.content.ContentAlreadyExistsException;
import com.academicregister.shared.exception.course.CourseNotFoundException;
import com.academicregister.shared.exception.student.StudentNotFoundException;
import com.academicregister.shared.exception.subject.SubjectNotFoundException;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContentServiceImpl implements IContentService {

    private final IContentRepository contentRepository;
    private final ICourseRepository courseRepository;
    private final ISubjectRepository subjectRepository;

    public ContentServiceImpl(IContentRepository contentRepository, ICourseRepository courseRepository, ISubjectRepository subjectRepository) {
        this.contentRepository = contentRepository;
        this.courseRepository = courseRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public Content createContent(String course, String subject) {
        Option<Content> opContent = Option.of(contentRepository.findByCourseAndSubject(course, subject));
        opContent.exists( rs -> {
            throw new ContentAlreadyExistsException(course, subject);
        });

        Try<Course> rsCourse = Try.of(() -> courseRepository.findByName(course));
        rsCourse.getOrElseThrow(throwable -> {
            throw new CourseNotFoundException(course);
        });

        Try<Subject> rsSubject = Try.of(() -> subjectRepository.findByTitle(subject));
        rsSubject.getOrElseThrow(throwable -> {
            throw new SubjectNotFoundException(subject);
        });

        var content = Content.builder()
            .course(rsCourse.get().getId())
            .subject(rsSubject.get().getId())
            .build();
        contentRepository.save(content);
        return content;
    }

    @Override
    public List<Subject> findSubjectsByCourse(String course) {
        Try<Course> rsCourse = Try.of(() -> courseRepository.findByName(course));
        rsCourse.getOrElseThrow(throwable -> {
            throw new CourseNotFoundException(course);
        });

        var contents = contentRepository.findByCourse(rsCourse.get().getId());
        List<Subject> subjects = new ArrayList<Subject>();
        contents.stream().forEach(c -> subjects.add(Subject.builder()
                .id(c.getSubject())
                .title(subjectRepository.findById(c.getSubject()).getTitle())
                .build())
        );
        return subjects;
    }
}
