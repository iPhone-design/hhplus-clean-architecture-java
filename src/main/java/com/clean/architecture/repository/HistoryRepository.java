package com.clean.architecture.repository;

import com.clean.architecture.domain.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.lang.model.element.Name;
import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Integer> {
    @Query(value = "SELECT history_no" +
            "            , user_id" +
            "            , schdule_no" +
            "            , create_date" +
            "         FROM history" +
            "        WHERE user_id = :user_id", nativeQuery = true)
    List<History> findAllMyHistoryByUserId(@Param("user_id") Long userId);
}