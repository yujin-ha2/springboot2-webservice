package com.boot.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/*
Application.class -> 메인클래스

@EnableJpaAuditing
: JPA Auditing 어노테이션들을 모두 활성화하도록 하는 어노테이션

@SpringBootApplication
: 스프링부트의 자동 설정, 스프링 Bean읽기/생성 모두 자동 설정된다

내장 WAS(Web Application Server)
: 외부에 WAS를 두지않고 애플리케이션 실행시 내부에서 WAS를 실행하는 것, 서버에 톰캣 설치할 필요 없어짐
*/

@EnableJpaAuditing
@SpringBootApplication
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args); //내장 WAS 실행
    }
}
