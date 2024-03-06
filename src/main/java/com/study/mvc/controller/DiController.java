package com.study.mvc.controller;

import com.study.mvc.diandIoc.DiRepository;
import com.study.mvc.diandIoc.DiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DiController {

    /**
     * DI(dependency injection): 의존성 주입
     *
     *
     */

    @GetMapping("/di")
    public ResponseEntity<?> diTest() {
        DiRepository diRepository = new DiRepository();
        DiService diService = new DiService(diRepository);

        Map<String, Object> responseData =
                Map.of("total", diService.getTotal(),
                        "avg", diService.getAverage()
                );

        return ResponseEntity.ok(responseData);
    }

}