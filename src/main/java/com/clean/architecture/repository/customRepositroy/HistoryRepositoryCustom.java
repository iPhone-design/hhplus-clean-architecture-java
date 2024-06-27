package com.clean.architecture.repository.customRepositroy;

import com.clean.architecture.domain.dto.HistoryDto;
import com.clean.architecture.domain.dto.ResponseDto;

import java.util.List;

public interface HistoryRepositoryCustom {
    ResponseDto saveHistory(HistoryDto historyDto);
    List<HistoryDto> findAllHistoryByUserId(HistoryDto historyDto);
}