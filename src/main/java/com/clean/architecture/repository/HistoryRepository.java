package com.clean.architecture.repository;

import com.clean.architecture.domain.entity.History;
import com.clean.architecture.repository.customRepositroy.HistoryRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Integer>, HistoryRepositoryCustom {
}