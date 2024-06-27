package com.clean.architecture.repository;

import com.clean.architecture.domain.entity.Registration;
import com.clean.architecture.repository.customRepositroy.RegistrationRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Integer>, RegistrationRepositoryCustom {
}
