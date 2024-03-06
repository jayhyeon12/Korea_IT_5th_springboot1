package com.study.mvc.service;

import com.study.mvc.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;

    @Override
    public List<?> getStudentList() {
        List<Map<String, String>> studentMapList = new ArrayList<>();
        List<String> studentList = studentRepository.getStudentListAll();

        for(String name : studentList) {
            studentMapList.add(Map.of("name", name));
        }
        return studentMapList;
    }

    @Override
    public Object getStudent(int index) {
        String studentName = studentRepository.findStudentNameByIndex(index);
        return Map.of("name", studentName);
    }
}
