package com.clean.architecture.service.serviceImpl;

import com.clean.architecture.domain.dto.HistoryDto;
import com.clean.architecture.domain.dto.RegistrationDto;
import com.clean.architecture.domain.dto.RequestDto;
import com.clean.architecture.domain.dto.ResponseDto;
import com.clean.architecture.repository.HistoryRepository;
import com.clean.architecture.repository.RegistrationRepository;
import com.clean.architecture.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectureServiceImpl implements LectureService {
    RegistrationRepository registrationRepository;
    HistoryRepository historyRepository;

    @Autowired
    public LectureServiceImpl(RegistrationRepository registrationRepository, HistoryRepository historyRepository) {
        this.registrationRepository = registrationRepository;
        this.historyRepository = historyRepository;
    }

    /**
     * 특강 신청 API
     * - 특정 userId 로 선착순으로 제공되는 특강을 신청하는 API 를 작성합니다.
     * - 동일한 신청자는 한 번의 수강 신청만 성공할 수 있습니다.
     * - 이미 신청자가 30명이 초과되면 이후 신청자는 요청을 실패합니다.
     * - 어떤 유저가 특강을 신청했는지 히스토리를 저장해야한다.
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

        ResponseDto responseDto = new ResponseDto();

        try {
            // 등록 내역 조회
            RegistrationDto paramRegistrationDto = new RegistrationDto();
            paramRegistrationDto.setScheduleNo(scheduleNo);
            List<RegistrationDto> listRegistrationDto = registrationRepository.findAllRegistrationByScheduleNo(paramRegistrationDto);
            
            // 동일한 신청자 체크
            long resultCnt = listRegistrationDto.stream().filter((registrationDto) -> (userId).equals(registrationDto.getUserId())).count();
            if (resultCnt > 0) {
                responseDto.setMessage("이미 특강 신청 완료된 학생입니다.");
                responseDto.setSuccess(false);
                return responseDto;
            }
            
            // 신청자 30명 초과 체크 => TODO 추후에 최대 신청자 수 컬럼을 추가 하여 관리 
            if (listRegistrationDto.size() >= 30) {
                responseDto.setMessage("특강 신청 인원 마감되었습니다. (30명)");
                responseDto.setSuccess(false);
                return responseDto;
            }

            // 특강 등록
            paramRegistrationDto.setUserId(userId);
            registrationRepository.saveRegistration(paramRegistrationDto);

            // 히스토리 저장
            HistoryDto historyDto = new HistoryDto();
            historyDto.setUserId(userId);
            historyDto.setScheduleNo(scheduleNo);
            historyRepository.saveHistory(historyDto);

            responseDto.setMessage("특강 신청 성공");
            responseDto.setSuccess(true);
        }
        catch (Exception e) {
            responseDto.setMessage(e.getMessage());
            responseDto.setSuccess(false);
        }
        finally {

        }

        return responseDto;
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
