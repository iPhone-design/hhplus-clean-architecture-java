package com.clean.architecture.repository;

import com.clean.architecture.domain.dto.HistoryDto;
import com.clean.architecture.domain.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Integer> {
    List<History> findAllMyHistoryByUserId(HistoryDto historyDto);
}