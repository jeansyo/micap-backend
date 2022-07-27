package com.educational.educational.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "scores")
public class Scores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user")
    private Integer user;

    @Column(name = "evaluation")
    private Integer evaluation;

    @Column(name = "score")
    private Integer score;
}
