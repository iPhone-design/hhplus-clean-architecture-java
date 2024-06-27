package com.clean.architecture.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LECTURE_SCHEDULE")
public class LectureSchedule {
    @Id
    @Column(name = "schedule_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer scheduleNo;

    @ManyToOne(targetEntity = Lecture.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_no")
    Lecture lecture;

    @Column(name = "open_date", nullable = false)
    private Timestamp openDate;
}