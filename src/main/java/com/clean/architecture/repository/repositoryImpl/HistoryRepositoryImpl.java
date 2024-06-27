package com.clean.architecture.repository.repositoryImpl;

import com.clean.architecture.domain.dto.HistoryDto;
import com.clean.architecture.domain.dto.ResponseDto;
import com.clean.architecture.domain.entity.History;
import com.clean.architecture.domain.entity.LectureSchedule;
import com.clean.architecture.domain.entity.Student;
import com.clean.architecture.repository.customRepositroy.HistoryRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class HistoryRepositoryImpl implements HistoryRepositoryCustom {

    EntityManager em;

    @Autowired
    public HistoryRepositoryImpl(EntityManager entityManager) {
        this.em = entityManager;
    }

    /**
     * 히스토리 저장
     *
     * @author  양종문
     * @since   2024-06-27
     * @param   historyDto
     * @return  responseDto
     */
    @Override
    @Transactional
    public ResponseDto saveHistory(HistoryDto historyDto) {
        Long userId = historyDto.getUserId();
        Integer scheduleNo = historyDto.getScheduleNo();

        // ResponseDto 객체 생성
        ResponseDto responseDto = new ResponseDto();

        try {
            // Student Entity 생성
            Student student = new Student();
            student.setUserId(userId);

            // LectureSchedule Entity 생성
            LectureSchedule lectureSchedule = new LectureSchedule();
            lectureSchedule.setScheduleNo(scheduleNo);

            // Registration Entity 생성
            History history = new History(student, lectureSchedule);

            // 저장
            em.persist(history);
            responseDto.setSuccess(true);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            em.close();
        }

        return responseDto;
    }

    /**
     * 히스토리 조회
     *
     * @author  양종문
     * @since   2024-06-27
     * @param   historyDto
     * @return  listHistoryDto
     */
    @Override
    @Transactional
    public List<HistoryDto> findAllHistoryByUserId(HistoryDto historyDto) {
        // List<HistoryDto> 객체 생성
        List<HistoryDto> listHistoryDto = new ArrayList<>();

        try {
            // 조회
            String query = "SELECT h FROM History h WHERE h.student.userId = :userId";
            List<History> typedQuery = em.createQuery(query, History.class).setParameter("userId", historyDto.getUserId()).getResultList();

            for (History history : typedQuery) {
                HistoryDto temp = new HistoryDto();
                temp.setHistoryNo(temp.getHistoryNo());
                temp.setUserId(temp.getUserId());
                temp.setScheduleNo(temp.getScheduleNo());
                temp.setCreateDate(temp.getCreateDate());
                listHistoryDto.add(temp);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            em.close();
        }

        return listHistoryDto;
    }
}