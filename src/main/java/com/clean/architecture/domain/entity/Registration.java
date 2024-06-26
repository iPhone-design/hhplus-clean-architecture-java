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
@Table(name = "REGISTRATION")
public class Registration {
    @Id
    @Column(name = "registration_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer registrationNo;

    @ManyToOne
    Student student;

    @ManyToOne
    LectureSchedule lectureSchedule;

    @Column(name = "registration_date", nullable = false)
    @CreationTimestamp
    private Timestamp registrationDate;
}

