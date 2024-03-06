package com.study.mvc.diandIoc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component // Ioc에 등록
public class IocService {

    @Autowired
    private IocRepository iocRepository;

    public String getJson() throws JsonProcessingException {
        Map<String, String> nameMap = iocRepository.convertNameMap();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(nameMap);

    }
}
