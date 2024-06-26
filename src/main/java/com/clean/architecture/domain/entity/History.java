package com.clean.architecture.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "HISTORY")
public class History {
    @Id
    @Column(name = "history_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer historyNo;

    @ManyToOne
    private Student student;

    @ManyToOne
    private LectureSchedule lectureSchedule;

    @Column(name = "create_date", nullable = false)
    @CreationTimestamp
    private Timestamp createDate;
}
