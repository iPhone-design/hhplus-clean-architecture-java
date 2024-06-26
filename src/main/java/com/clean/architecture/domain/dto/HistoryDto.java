package com.clean.architecture.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class HistoryDto {
    private Integer historyNo;
    private Long userId;
    private Integer scheduleNo;
    private Timestamp createDate;
    private List<RegistrationDto> listRegistrationDto;
}
