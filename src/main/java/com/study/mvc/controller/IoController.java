package com.study.mvc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.study.mvc.diandIoc.IocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IoController {

    @Autowired
    private IocService iocService;

    @GetMapping("/ioc")
    public ResponseEntity<?> iocTest() throws JsonProcessingException {
       String json = iocService.getJson();

        return ResponseEntity.ok(json);

    }

    /**
     * 학생 추가 조회
     * Controller
     * - StudentRestController: get 요청(/ex/students), (/ex/student/0)
     * Service
     * - StudentService(interface): getStudentList(), getStudent(int index)
     * - StudentServiceImpl(class)
     * repository
     * - StudentRepository(interface): getStudentListAll(), findStudentNameByIndex(int index)
     * - StudentRepositoryImpl(class): studentList = ["1", "2", "3"] (전역에서 생성)
     *
     * 응답: ok [{name: "1"}, {name: "2"}, {name: "3"}], 응답: ok {name: "1"}
     */

}
