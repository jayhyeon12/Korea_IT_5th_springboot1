package com.study.mvc.controller;

import com.study.mvc.dto.HelloDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudyRestController {
    @GetMapping("/hello/test")
    public String hello(HelloDto helloDto) {
        System.out.println(helloDto);
        return "Hello";
    }

    /**
     * Controller: StudentController
     * 메소드: get
     * URL: /student
     * Params: name, age, phone, address
     * 응답데이터: Json(name, age, phone, address)
     */
}