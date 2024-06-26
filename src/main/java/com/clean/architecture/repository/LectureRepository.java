package com.clean.architecture.repository;

import com.clean.architecture.domain.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Integer> {
}
