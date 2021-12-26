package com.spring.blog.post.domain.content;

import com.spring.blog.post.exception.PostContentException;
import com.spring.blog.post.exception.PostTitleException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostContent {
    private static final int MAX_TITLE_LENGTH = 50;
    private static final int MAX_CONTENT_LENGTH = 10000;

    @NotEmpty
    @Column(nullable = false, length = 50)
    private String title;

    @NotEmpty
    @Column(nullable = false, length = 10000)
    private String content;

    public PostContent(String title, String content) {
        validateTitle(title);
        validateContent(content);
        this.title = title;
        this.content = content;
    }

    //타이틀에 대한 검사
    private void validateTitle(String title) {
        if (Objects.isNull(title)) {
            throw new PostTitleException();
        }

        String trimTitle = title.trim();
        if (title.length() > MAX_TITLE_LENGTH || trimTitle.isEmpty()) {
            throw new PostTitleException();
        }
    }

    //게시글 내용 검사
    private void validateContent(String content) {
        if (Objects.isNull(content)) {
            throw new PostContentException();
        }

        String trimContent = content.trim();
        if (content.length() > MAX_CONTENT_LENGTH || content.isEmpty()) {
            throw new PostContentException();
        }
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
