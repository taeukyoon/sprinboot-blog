package com.spring.blog.Post.content;

import com.spring.blog.post.domain.content.PostContent;
import com.spring.blog.post.exception.PostContentException;
import com.spring.blog.post.exception.PostTitleException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Collections;

import static org.assertj.core.api.Assertions.*;

@DisplayName("PostContent 도메인 단위테스트")
public class PostContentTest {

    @Test
    @DisplayName("게시물이 제목이 50자이내면 정상생성이 됩니다.")
    public void postContent_title_create_from_0_to_50() throws Exception {
        //given
        String title = ("gfd");

        //when
        PostContent postContent = new PostContent(title, "spring");

        //then
        assertThat(postContent)
                .usingRecursiveComparison() //동등성 (필드값 비교)
                .isEqualTo(new PostContent(title, "spring"));
    }

    @Test
    @DisplayName("게시글의 제목이 50자를 초과하면 예외가 발생합니다.")
    public void postContent_title_over_50 () throws Exception {
        //given
        String title = String.join("", Collections.nCopies(30, "hi"));

        //when, then
        assertThatCode(() -> new PostContent(title, "spring"))
                .isInstanceOf(PostTitleException.class)
                .hasMessage("게시글은 공백이나 50자를 초과할 수 없습니다.")
                .hasFieldOrPropertyWithValue("errorCode", "P0002")
                .hasFieldOrPropertyWithValue("httpStatus", HttpStatus.BAD_REQUEST);
     }

     @Test
     @DisplayName("게시글의 제목이 null 또는 공백이면 예외가 발생합니다.")
     public void postContent_title_null_or_blank() throws Exception {
         //given
         String title = ("");
         //when, then
         assertThatCode(() -> new PostContent(title, "spring"))
                 .isInstanceOf(PostTitleException.class)
                 .hasMessage("게시글은 공백이나 50자를 초과할 수 없습니다.")
                 .hasFieldOrPropertyWithValue("errorCode", "P0002")
                 .hasFieldOrPropertyWithValue("httpStatus", HttpStatus.BAD_REQUEST);
      }

      @Test
      @DisplayName("게시글의 내용이 1~10000자리 이내면 정상생성이 됩니다.")
      public void postContent_create_from_1_to_10000() throws Exception {
          // given, when
          PostContent postContent = new PostContent("title", "spring");

          // then
          assertThat(postContent)
                  .usingRecursiveComparison()
                  .isEqualTo(new PostContent("title", "spring"));
       }

       @Test
       @DisplayName("게시글의 내용이 1~10000자리를 초과하면 예외가 발생합니다.")
       public void postContent_content_over_10000() throws Exception {
           // given, when,
           String content = String.join("",Collections.nCopies(10001,"d"));
           // then
           assertThatCode(() -> new PostContent("title", content))
                   .isInstanceOf(PostContentException.class)
                   .hasMessage("게시글의 내용은 1 ~ 10000자까지만 가능합니다.")
                   .hasFieldOrPropertyWithValue("errorCode", "P0001")
                   .hasFieldOrPropertyWithValue("httpStatus", HttpStatus.BAD_REQUEST);
        }

        @Test
        @DisplayName("게시글의 내용이 null 또는 공백이면 예외가 발생합니다.")
        public void postContent_content_null_or_blank() throws Exception {
            // given, when
            String content = "";
            // then
            assertThatCode(() -> new PostContent("title", content))
                    .isInstanceOf(PostContentException.class)
                    .hasMessage("게시글의 내용은 1 ~ 10000자까지만 가능합니다.")
                    .hasFieldOrPropertyWithValue("errorCode", "P0001")
                    .hasFieldOrPropertyWithValue("httpStatus", HttpStatus.BAD_REQUEST);
         }
}
