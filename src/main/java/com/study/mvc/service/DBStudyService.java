package com.study.mvc.service;

import com.study.mvc.dto.DBStudyReqDto;
import com.study.mvc.dto.DBStudyInsertRespDto;
import com.study.mvc.dto.DBStudySelectRespDto;
import com.study.mvc.entity.Study;
import com.study.mvc.repository.DBStudyRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Service
public class DBStudyService {

    @Autowired
    private DBStudyRepository dbStudyRepository;

    public DBStudyInsertRespDto createStudy(@RequestBody DBStudyReqDto dbStudyReqDto) {
//        [Allargs]
//        Study study = new Study(0, dbStudyReqDto.getName(), dbStudyReqDto.getAge(), null);

//        [Noargs]
//        Study study = new Study();
//        study.setName(dbStudyReqDto.getName());
//        study.setAge(dbStudyReqDto.getAge());

        Study findStudy = dbStudyRepository.findStudyByName(dbStudyReqDto.getName());

        if(findStudy != null) {
            return DBStudyInsertRespDto.builder()
                    .successStatus(false)
                    .build();
        }

        Study study = Study.builder()
                .age(dbStudyReqDto.getAge())
                .name(dbStudyReqDto.getName())
                .build();

        int successCount = dbStudyRepository.save(study);

        DBStudyInsertRespDto dbStudyInsertRespDto = DBStudyInsertRespDto.builder()
                .id(study.getId())
                .name(study.getName())
                .age(study.getAge())
                .successStatus(successCount > 0)
                .successCount(successCount)
                .build();

        return dbStudyInsertRespDto;
    }

    public DBStudySelectRespDto findStudyByID(int id) {
        Study study = dbStudyRepository.findStudyById(id);
        System.out.println(study);

        DBStudySelectRespDto dbStudySelectRespDto =
                DBStudySelectRespDto.builder()
                        .id(study.getId())
                        .age(study.getAge())
                        .name(study.getName())
                        .build();

        return dbStudySelectRespDto;
    }

    public DBStudySelectRespDto findStudyByName(String name) {
        Study study = dbStudyRepository.findStudyByName(name);

        return study == null ? null : study.toDto();
    }

    public List<DBStudySelectRespDto> findAll() {
        List<DBStudySelectRespDto> respDtosList = new ArrayList<>();
        List<Study> studyList = dbStudyRepository.findAll();

        for(Study study : studyList) {
            respDtosList.add(study.toDto());
        }
        return respDtosList;
    }

    public List<DBStudySelectRespDto> findAll2() {
        return dbStudyRepository.findAll()
                .stream()
                .map(Study::toDto)
                .collect(Collectors.toList());
    }

    public int deleteById(int id) {
        return dbStudyRepository.deleteById(id);
    }

    public int putById(int id, DBStudyReqDto dbStudyReqDto) {
        Study study = dbStudyReqDto.toEntity(id);
        return dbStudyRepository.putById(study);
    }

    public int patchById(int id, DBStudyReqDto dbStudyReqDto) {
        return dbStudyRepository.putById(dbStudyReqDto.toEntity(id));
    }
}
