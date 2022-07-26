package com.educational.educational.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "evaluations")
public class Evaluations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "course")
    private Integer course;
    @Column(name = "start")
    private String start;
    @Column(name = "end")
    private String end;
    @Column(name = "status")
    private Integer status = 1;

}
