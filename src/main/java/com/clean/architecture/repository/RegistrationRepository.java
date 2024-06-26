package com.clean.architecture.repository;

import com.clean.architecture.domain.dto.RegistrationDto;
import com.clean.architecture.domain.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Integer> {
    List<Registration> findMyRegistrationByUserId(RegistrationDto registrationDto);
}
