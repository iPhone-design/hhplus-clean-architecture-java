package com.clean.architecture.repository.repositoryImpl;

import com.clean.architecture.domain.dto.RegistrationDto;
import com.clean.architecture.domain.dto.ResponseDto;
import com.clean.architecture.domain.entity.LectureSchedule;
import com.clean.architecture.domain.entity.Registration;
import com.clean.architecture.domain.entity.Student;
import com.clean.architecture.repository.customRepositroy.RegistrationRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RegistrationRepositoryImpl implements RegistrationRepositoryCustom {

    EntityManager em;

    @Autowired
    public RegistrationRepositoryImpl(EntityManager entityManager) {
        this.em = entityManager;
    }

    /**
     * 등록 저장
     *
     * @author  양종문
     * @since   2024-06-27
     * @param   registrationDto
     * @return  responseDto
     */
    @Override
    @Transactional
    public ResponseDto saveRegistration(RegistrationDto registrationDto) {
        Long userId = registrationDto.getUserId();
        Integer scheduleNo = registrationDto.getScheduleNo();

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
            Registration registration = new Registration(student, lectureSchedule);

            // 저장
            em.persist(registration);
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
     * 등록 조회
     *
     * @author  양종문
     * @since   2024-06-27
     * @param   registrationDto
     * @return  listRegistrationDto
     */
    @Override
    @Transactional
    public List<RegistrationDto> findAllRegistrationByUserId(RegistrationDto registrationDto) {
        // List<RegistrationDto> 객체 생성
        List<RegistrationDto> listRegistrationDto = new ArrayList<>();

        try {
            // 조회
            String query = "SELECT r FROM Registration r WHERE r.student.userId = :userId";
            List<Registration> typedQuery = em.createQuery(query, Registration.class).setParameter("userId", registrationDto.getUserId()).getResultList();

            for (Registration registration : typedQuery) {
                RegistrationDto temp = new RegistrationDto();
                temp.setRegistrationNo(registration.getRegistrationNo());
                temp.setUserId(registration.getStudent().getUserId());
                temp.setScheduleNo(registration.getLectureSchedule().getScheduleNo());
                temp.setRegistrationDate(registration.getRegistrationDate());
                listRegistrationDto.add(temp);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            em.close();
        }

        return listRegistrationDto;
    }
}
