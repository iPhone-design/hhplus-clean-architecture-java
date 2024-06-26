package com.clean.architecture.repository;

import com.clean.architecture.domain.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Integer> {
}