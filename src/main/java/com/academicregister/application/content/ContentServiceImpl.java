package com.academicregister.application.content;

import com.academicregister.domain.content.Content;
import com.academicregister.domain.content.IContentRepository;
import com.academicregister.shared.exception.content.ContentAlreadyExistsException;
import io.vavr.control.Option;
import org.springframework.stereotype.Service;

@Service
public class ContentServiceImpl implements IContentService {

    private final IContentRepository contentRepository;

    public ContentServiceImpl(IContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @Override
    public Content createContent(Content content) {
        Option<Content> opContent = Option.of(contentRepository.findByCourseAndSubject(content.getCourseId(), content.getSubjectId()));
        opContent.exists( rs -> {
            throw new ContentAlreadyExistsException(content.getCourseId(), content.getSubjectId());
        });
        contentRepository.save(content);
        return content;
    }
}
