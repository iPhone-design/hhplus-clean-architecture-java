package com.clean.architecture.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDto {
    private Long userId;
    private Integer lectureNo;
    private Integer scheduleNo;
}
