package com.clean.architecture.repository;

import com.clean.architecture.domain.entity.LectureSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureScheduleRepository extends JpaRepository<LectureSchedule, Integer> {
}