package com.study.mvc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.mvc.dto.StudentReqDto;
import com.study.mvc.dto.StudentResDto;
import com.study.mvc.entity.Student;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.spi.ObjectFactoryBuilder;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class StudentController {

    @PostMapping("/student")
    public ResponseEntity<?> addStudent(@CookieValue(required = false) String students, @RequestBody Student student) throws JsonProcessingException, UnsupportedEncodingException {
        ObjectMapper om = new ObjectMapper();

        List<Student> sl = new ArrayList<>();
        int lastId = 0;

        if (students != null) {
            if (!students.isBlank()) {
                for(Object object : om.readValue(students, List.class)) {
                    Map<String, Object> studentMap = (Map<String, Object>) object;
                    sl.add(om.convertValue(studentMap, Student.class));
                }

                lastId = sl.get(sl.size() - 1).getStudentId();
            }
        }

        student.setStudentId(lastId + 1);
        sl.add(student);

        String studentListJson = om.writeValueAsString(sl);

        ResponseCookie responseCookie = ResponseCookie
                .from("students", URLEncoder.encode(studentListJson, "UTF-8"))
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(60)
                .build();
        
        return ResponseEntity
                .created(null)
                .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                .body(student);
    }

    @GetMapping("/student")
    public ResponseEntity<?> getStudentInfo(StudentReqDto studentReqDto) {
        System.out.println(studentReqDto);
        return ResponseEntity.ok().body(studentReqDto.toResDto());

    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<?> getStudent(@PathVariable int studentId) {
        List<Student> sl= List.of(
                new Student(1, "이00"),
                new Student(2, "박00"),
                new Student(3, "최00")
        );
        Student findStudent = null;
        for(Student student : sl) {
            if (student.getStudentId() == studentId) {
                findStudent = student;
            }
        }

//        Optional<Student> os =
//                sl.stream().filter(student -> student.getStudentId() == studentId).findFirst();
//
//        if(os.isEmpty()) {
//            return ResponseEntity.badRequest().body(Map.of("errorMessage", "존재하지 않는 ID"));
//        }
//        findStudent = os.get();

        if(findStudent == null) {
            return ResponseEntity.badRequest().body(Map.of("errorMessage", "존재하지 않는 ID"));
        }

        return ResponseEntity.ok().body(findStudent);
    }

}
