package com.clean.architecture.service;

import com.clean.architecture.domain.dto.RequestDto;
import com.clean.architecture.domain.dto.ResponseDto;

public interface LectureService {
    ResponseDto applyLecture(RequestDto requestDto);
    ResponseDto checkLecture(RequestDto requestDto);
}
