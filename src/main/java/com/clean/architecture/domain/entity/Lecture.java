package com.clean.architecture.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LECTURE")
public class Lecture {
    @Id
    @Column(name = "lecture_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lectureNo;

    @Column(name = "lecture_name", nullable = false)
    private String lectureName;
}
