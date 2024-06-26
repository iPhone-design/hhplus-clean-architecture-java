package com.clean.architecture.service.serviceImpl;

import com.clean.architecture.domain.dto.RegistrationDto;
import com.clean.architecture.domain.dto.RequestDto;
import com.clean.architecture.domain.dto.ResponseDto;
import com.clean.architecture.repository.RegistrationRepository;
import com.clean.architecture.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectureServiceImpl implements LectureService {
    RegistrationRepository registrationRepository;

    @Autowired
    public LectureServiceImpl(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    /**
     * 특강 신청 API
     * - 특정 userId 로 선착순으로 제공되는 특강을 신청하는 API 를 작성합니다.
     * - TODO 동일한 신청자는 한 번의 수강 신청만 성공할 수 있습니다.
     * - TODO 특강은 `4월 20일 토요일 1시` 에 열리며, 선착순 30명만 신청 가능합니다.
     * - TODO 이미 신청자가 30명이 초과되면 이후 신청자는 요청을 실패합니다.
     * - TODO 어떤 유저가 특강을 신청했는지 히스토리를 저장해야한다.
     *
     * @author  양종문
     * @since   2024-06-27
     * @param   requestDto
     * @return  ResponseDto
     */
    @Override
    public ResponseDto applyLecture(RequestDto requestDto) {
        Long userId = requestDto.getUserId();
        Integer scheduleNo = requestDto.getScheduleNo();

        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setUserId(userId);
        registrationDto.setScheduleNo(scheduleNo);

        // 특강 신청
        return registrationRepository.saveRegistration(registrationDto);
    }

    /**
     * 특강 신청 완료 여부 조회 API
     * - 특정 userId 로 특강 신청 완료 여부를 조회하는 API 를 작성합니다.
     * - 특강 신청에 성공한 사용자는 성공했음을, 특강 등록자 명단에 없는 사용자는 실패했음을 반환합니다. (true, false)
     *
     * @author  양종문
     * @since   2024-06-27
     * @param   requestDto
     * @return  responseDto
     */
    @Override
    public ResponseDto checkLecture(RequestDto requestDto) {
        Long userId = requestDto.getUserId();
        
        // RegistrationDto 객체 생성
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setUserId(userId);

        // 등록 조회
        List<RegistrationDto> listRegistrationDto = registrationRepository.findAllRegistrationByUserId(registrationDto);
        
        // ResponseDto 객체 생성
        ResponseDto responseDto = new ResponseDto();
        responseDto.setListRegistrationDto(listRegistrationDto);
        responseDto.setSuccess(!listRegistrationDto.isEmpty());

        return responseDto;
    }
}
