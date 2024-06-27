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
@Table(name = "REGISTRATION")
public class Registration {
    @Id
    @Column(name = "registration_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer registrationNo;

    @ManyToOne(targetEntity = Student.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    Student student;

    @ManyToOne(targetEntity = LectureSchedule.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_no")
    LectureSchedule lectureSchedule;

    @Column(name = "registration_date", nullable = false)
    @CreationTimestamp
    private Timestamp registrationDate;

    public Registration(Student student, LectureSchedule lectureSchedule) {
        this.student = student;
        this.lectureSchedule = lectureSchedule;
    }
}

