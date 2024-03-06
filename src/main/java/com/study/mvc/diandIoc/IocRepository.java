package com.study.mvc.diandIoc;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class IocRepository {
    private List<String> nameList = List.of("klh", "wdh", "jse");

    public Map<String, String> convertNameMap() {
        Map<String, String> nameMap = new HashMap<>();
        for(int i = 0; i < nameList.size(); i++) {
            nameMap.put("name" + (i + 1), nameList.get(i));
        }
        return nameMap;
    }
}
