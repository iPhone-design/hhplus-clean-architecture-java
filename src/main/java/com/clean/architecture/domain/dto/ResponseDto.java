package com.clean.architecture.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseDto {
    private Boolean success;
    private String message;
    private HistoryDto historyDto;
    private List<RegistrationDto> listRegistrationDto;
}
