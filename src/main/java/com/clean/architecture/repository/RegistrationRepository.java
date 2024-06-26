package com.clean.architecture.repository;

import com.clean.architecture.domain.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Integer> {
    @Query(value = "SELECT registration_no" +
            "            , user_id" +
            "            , schdule_no" +
            "            , registration_date" +
            "         FROM registration" +
            "        WHERE user_id = :user_id", nativeQuery = true)
    List<Registration> findMyRegistrationByUserId(@Param("user_id") Long userId);
}
