package com.boot.book.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/*
@RunWith(SpringRunner.class)
: 테스트 진행시 SpringRunner라는 스프링 실행자를 사용, 스프링 부트 테스트와 JUnit 사이에 연결자 역할을 함

@Autowired
: 스프링이 관리하는 빈(Bean)으 주입받는다

@After
: Junit에서 단위 테스트가 끝날 때마다 수행되는 메소드 지정. 보통은 배포 전 전체 테스트를 수행할 때 테스트 간 데이터 침범을 막기위해 사용함 
*/

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()        //postsRepository.save : 테이블 posts에 id값이 있다면 update, 없다면 insert쿼리를 실행
                    .title(title)
                    .content(content)
                    .author("yujin@gmail.com")
                    .build());

        //when
        List<Posts> postsList = postsRepository.findAll();  //postsRepository.findAll : 테이블 posts에 있는 모든 데이터를 조회해오는 메소드

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.of(2021, 12, 21, 0, 0, 0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>> createDate=" + posts.getCreatedDate() + ", modifiedDate=" + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
