package com.boot.book.springboot.web;

import com.boot.book.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
@RestController
: 컨트롤러를 JSON을 반환하는 컨트롤러로 만들어줌

@GetMapping("/hello")
: HTTP Method인 GET의 요청을 받을 수 있는 API를 만들어줌, 예전) @RequestMapping(method= RequestMethod.GET)

@RequestParam
: 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션
  (예) @RequestParam("name") String name : 외부에서 @RequestParam("name")으로 넘긴 파라미터를 메소드 파라미터 String name에 저장한다
*/

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount){
        return new HelloResponseDto(name, amount);
    }
}
