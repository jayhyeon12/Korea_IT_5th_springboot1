package com.study.mvc.diandIoc;

import java.util.List;

public class DiRepository {

    private List<Integer> scoreList = List.of(100, 70, 90, 80);

    public List<Integer> getScoreList() {
        return scoreList;
    }
}
