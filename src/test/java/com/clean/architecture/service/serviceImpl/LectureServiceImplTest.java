package com.clean.architecture.service.serviceImpl;

import com.clean.architecture.domain.dto.RegistrationDto;
import com.clean.architecture.domain.dto.RequestDto;
import com.clean.architecture.domain.dto.ResponseDto;
import com.clean.architecture.repository.HistoryRepository;
import com.clean.architecture.repository.RegistrationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LectureServiceImplTest {
    private LectureServiceImpl lectureServiceImpl;
    private RegistrationRepository registrationRepository;
    private HistoryRepository historyRepository;

    @BeforeEach
    public void setUp() {
        registrationRepository = mock(RegistrationRepository.class);
        historyRepository = mock(HistoryRepository.class);
        lectureServiceImpl = new LectureServiceImpl(registrationRepository, historyRepository);
    }

    @Test
    @DisplayName("동시성 특강 신청")
    @Transactional
    void applyLecture() throws ExecutionException, InterruptedException {
        // given
        int threadCount = 40;
        int allowStudentCnt = 30;
        List<CompletableFuture<ResponseDto>> futures = new ArrayList<>();

        RequestDto requestDto = new RequestDto();
        requestDto.setUserId(1L);
        requestDto.setScheduleNo(1);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setSuccess(true);
        when(lectureServiceImpl.applyLecture(requestDto)).thenReturn(responseDto);

        // when
        Integer scheduleNo = 1;
        for (int i = 0; i < threadCount; i++) {
            Long userId = 1L + i;
            CompletableFuture<ResponseDto> future = CompletableFuture.supplyAsync(() -> {
                RequestDto temp = new RequestDto();
                temp.setUserId(userId);
                temp.setScheduleNo(scheduleNo);
                return lectureServiceImpl.applyLecture(temp);
            });
            futures.add(future);
        }

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        int successCount = 0;
        for (CompletableFuture<ResponseDto> future : futures) {
            ResponseDto response = future.get();
            if (response.getSuccess()) {
                successCount++;
            }
        }

        // then
        assertThat(successCount).isEqualTo(allowStudentCnt);
    }

    @Test
    @DisplayName("같은 유저 특강 신청")
    void sameUserApplyLecture() throws ExecutionException, InterruptedException {
        // given
        List<CompletableFuture<ResponseDto>> futures = new ArrayList<>();
        int threadCount = 40;
        Integer scheduleNo = 1;
        Long userId = 1L;
        int resultCnt = 1;

        RequestDto requestDto = new RequestDto();
        requestDto.setUserId(1L);
        requestDto.setScheduleNo(1);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setSuccess(true);
        when(lectureServiceImpl.applyLecture(requestDto)).thenReturn(responseDto);

        // when
        for (int i = 0; i < threadCount; i++) {
            CompletableFuture<ResponseDto> future = CompletableFuture.supplyAsync(() -> {
                RequestDto paramTest = new RequestDto();
                paramTest.setUserId(userId);
                paramTest.setScheduleNo(scheduleNo);
                return lectureServiceImpl.applyLecture(paramTest);
            });
            futures.add(future);
        }

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        int successCount = 0;

        for (CompletableFuture<ResponseDto> future : futures) {
            ResponseDto response = future.get();
            if (response.getSuccess()) {
                successCount++;
            }
        }

        // then
        assertThat(successCount).isEqualTo(resultCnt);
    }
}