package com.boot.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

/*
- 인터페이스를 생성 후, JpaRepository<Entiry클래스, PK타입>를 상속하면 기본적인 CRUD 메소드가 자동으로 생성된다
- 프로젝트 규모가 커져 도메인 별로 프로젝트를 분리해야 한다면 Entity클래스는 기본 Repository와 함께 움직여야 하므로 도메인 패키지에서 함께 관리함
*/

public interface PostsRepository extends JpaRepository<Posts, Long> {

}
