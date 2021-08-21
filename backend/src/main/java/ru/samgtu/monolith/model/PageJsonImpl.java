package ru.samgtu.monolith.model;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import ru.samgtu.monolith.config.JacksonViews;

import java.util.List;
import java.util.function.Function;

/**
 * Creation date: 17.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
public class PageJsonImpl<T> extends PageImpl<T> {
    public PageJsonImpl(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public PageJsonImpl(Page<T> page, Pageable pageable) {
        super(page.getContent(), pageable, page.getTotalElements());
    }

    public PageJsonImpl(Page<T> page) {
        this(page, page.getPageable());
    }

    @JsonView(JacksonViews.Public.class)
    public int getTotalPages() {
        return super.getTotalPages();
    }

    @JsonView(JacksonViews.Public.class)
    public long getTotalElements() {
        return super.getTotalElements();
    }

    @JsonView(JacksonViews.Public.class)
    public boolean hasNext() {
        return super.hasNext();
    }

    @JsonView(JacksonViews.Public.class)
    public boolean isLast() {
        return super.isLast();
    }

    @JsonView(JacksonViews.Public.class)
    public boolean hasContent() {
        return super.hasContent();
    }

    @JsonView(JacksonViews.Public.class)
    public List<T> getContent() {
        return super.getContent();
    }

    @Override
    public <U> Page<U> map(Function<? super T, ? extends U> converter) {
        return new PageJsonImpl<>(getConvertedContent(converter), getPageable(), getTotalElements());
    }
}
