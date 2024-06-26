package com.clean.architecture.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class RegistrationDto {
    private Integer registrationNo;
    private Long userId;
    private Integer scheduleNo;
    private Timestamp registrationDate;
}

