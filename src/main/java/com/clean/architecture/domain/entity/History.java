package com.clean.architecture.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "HISTORY")
public class History {
    @Id
    @Column(name = "history_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer historyNo;

    @ManyToOne(targetEntity = Student.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Student student;

    @ManyToOne(targetEntity = LectureSchedule.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_no")
    private LectureSchedule lectureSchedule;

    @Column(name = "create_date", nullable = false)
    @CreationTimestamp
    private Timestamp createDate;

    public History(Student student, LectureSchedule lectureSchedule) {
        this.student = student;
        this.lectureSchedule = lectureSchedule;
    }
}
