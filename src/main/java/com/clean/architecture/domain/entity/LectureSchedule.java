package com.clean.architecture.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "LECTURE_SCHEDULE")
public class LectureSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int schedule_no;

    @ManyToOne
    Lecture lecture;

    private Timestamp openDate;
}

