package com.spring.blog.post.domain;

import com.spring.blog.common.DateTime;
import com.spring.blog.post.domain.content.PostContent;
import com.spring.blog.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private PostContent postContent;

    @Embedded
    private DateTime dateTime;

    public Post(Long id, User user, PostContent postContent, DateTime dateTime) {
        this.id = id;
        this.postContent = postContent;
        this.dateTime = dateTime;
    }
}
