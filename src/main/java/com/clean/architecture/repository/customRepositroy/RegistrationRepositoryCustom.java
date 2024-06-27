package com.clean.architecture.repository.customRepositroy;

import com.clean.architecture.domain.dto.RegistrationDto;
import com.clean.architecture.domain.dto.ResponseDto;

import java.util.List;

public interface RegistrationRepositoryCustom  {
    ResponseDto saveRegistration(RegistrationDto registrationDto);
    List<RegistrationDto> findAllRegistrationByScheduleNo(RegistrationDto registrationDto);
    List<RegistrationDto> findAllRegistrationByUserId(RegistrationDto registrationDto);
}
