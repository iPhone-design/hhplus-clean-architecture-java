package com.clean.architecture.controller;

import com.clean.architecture.domain.dto.RequestDto;
import com.clean.architecture.domain.dto.ResponseDto;
import com.clean.architecture.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lectures")
public class LectureController {
    LectureService lectureService;

    @Autowired
    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    /**
     * 특강 신청 API
     *
     * @author  양종문
     * @since   2024-06-27
     * @param   requestDto
     * @return  ResponseDto
     */
    @PostMapping("/apply")
    public ResponseDto applyLecture(@RequestBody RequestDto requestDto) throws Exception {
        return lectureService.applyLecture(requestDto);
    }

    /**
     * 특강 신청 완료 여부 조회 API
     *
     * @author  양종문
     * @since   2024-06-27
     * @param   userId
     * @return  ResponseDto
     */
    @GetMapping("/application/{userId}")
    public ResponseDto checkLecture(@PathVariable Long userId) {
        // RequestDto 객체 생성
        RequestDto requestDto = new RequestDto();
        requestDto.setUserId(userId);

        return lectureService.checkLecture(requestDto);
    }
}
