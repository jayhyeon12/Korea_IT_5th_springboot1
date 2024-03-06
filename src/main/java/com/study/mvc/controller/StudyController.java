package com.study.mvc.controller;
import com.study.mvc.model.HelloModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.Map;

@Controller
public class StudyController {

    //mvc
    @GetMapping("/hello")
    public String helloPage(Model model) {
        HelloModel hm = HelloModel.builder()
                .name1("이00")
                .name2("강00")
                .name2("주00")
                .build();

        model.addAttribute("name", "kjh");
        return "hello";
    }

    // Rest
    @GetMapping("/test")
    @ResponseBody
    public Map<String, Object> testPage() {
        Map<String, Object> testObj = new HashMap<>();
        testObj.put("age", "20");
        return testObj;
    }
}