package com.boot.book.springboot.web;

import com.boot.book.springboot.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.is;

/*
@RunWith(SpringRunner.class)
: 테스트 진행시 SpringRunner라는 스프링 실행자를 사용, 스프링 부트 테스트와 JUnit 사이에 연결자 역할을 함

@WebMvcTest
: 스트링 테스트 어노테이션 중, Web(Spring MVC)에 집중하는 어노테이션. 선언시 @Controller, @ControllerAdvice 등 사용 가능
  참고로 @WebMvcTest는 JPA 기능은 작동하지 않기 때문에 JPA 기능까지 한번에 테스트하려는 경우 @SpringBootTest와 TestRestTemplate를 사용한다.

@Autowired
: 스프링이 관리하는 빈(Bean)으 주입받는다
*/

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class,
            excludeFilters = {
            @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
            }
)
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;    //스프링 MVC 테스트의 시작점, 이 클래스로 HTTP GET, POST 등 API 테스트를 할 수 있다

    @WithMockUser(roles = "USER")
    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello"))            //HTTP GET 요청, 체이닝 지원되어 이어서 선언함
                .andExpect(status().isOk())             //HTTP Header의 Status를 검증. 흔히 아는 200, 404, 500 중 200(OK)인지 검증
                .andExpect(content().string(hello));    //응답 본문의 내용을 검증
    }

    @WithMockUser(roles = "USER")
    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                    get("/hello/dto")
                                    .param("name", name)
                                    .param("amount", String.valueOf(amount)))   //param: API 테스트시 요청 파라미터 설정. String만 허용됨
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))    //jsonPath: JSON 응답값을 필드별로 검증하는 메소드. $를 기준으로 필드명 명시
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
