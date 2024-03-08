package com.study.mvc.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DBStudySelectRespDto {
    private int id;
    private String name;
    private int age;
}
