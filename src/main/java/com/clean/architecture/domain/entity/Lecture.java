package com.clean.architecture.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "LECTURE")
public class Lecture {
    @Id
    @Column(name = "lecture_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // GenerationType.IDENTITY : AUTO_INCREMENT
    private Integer lectureNo;

    @Column(name = "lecture_name", nullable = false)
    private String lectureName;
}
