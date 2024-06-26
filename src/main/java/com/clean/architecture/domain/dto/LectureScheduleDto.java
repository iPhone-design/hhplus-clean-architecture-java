package com.clean.architecture.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class LectureScheduleDto {
    private Integer scheduleNo;
    private Integer lectureNo;
    private Timestamp openDate;
}

