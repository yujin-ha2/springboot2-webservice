package com.boot.book.springboot.domain.posts;

import com.boot.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*
- lombok 어노테이션은 코드를 단순화해주지만 필수 어노테이션은 아니기 때문에 주요 어노테이션인 @Entity를 클래스에 가깝게둠
@NoArgsConstructor
: 기본 생성자 자동 추가. public Posts(){}와 동일
@Getter
: 클래스 내 모든 필드의 Getter 메소드 자동생성
@Build
: 해당 클래스의 빌더 패턴 클래스를 생성. 생성자 상단에 선언 시 생성자에 포함된 필드만 빌드에 포함.

- JPA에서 제공하는 어노테이션들
(참고) 웬만하면 Entity의 PK는 Long타입의 Auto_increment를 추천함 (Mysql기준 bigint타입) 유니크 키나 여러키를 조합한 복합키로 PK를 잡을 경우 난감한 상황이 종종 발생
@Entity
: 테이블과 링크될 클래스임을 나타냄. 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍(_)으로 테이블 이름 매칭함
  (예)SalesManager.java -> sales_manager table
@Id
: 해다 테이블의 PK필드를 나타냄
@GeneratedValue
: PK의 생성 규칙을 나타냄. 스프링부트2.0dptjsms GenerationType.IDENTITY 옵션을 추가해야 auto_increment가 됨
@Column
: 테이블 칼럼. 선언하지 않아도 해당 클래스 필드는 모두 칼럼이 되지만 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용됨
  (예)문자열의 경우 VARCHAR(255)가 기본값인데 사이즈를 500으로 늘리고 싶거나 타입을 TEXT로 변경하고 싶은 경우에 사용
*/

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
