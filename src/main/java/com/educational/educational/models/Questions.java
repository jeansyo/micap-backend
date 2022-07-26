package com.educational.educational.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "questions")
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "evaluation")
    private Integer evaluation;

    @Column(name = "firstanswer")
    private String firstanswer;
    @Column(name = "secondanswer")
    private String secondanswer;
    @Column(name = "thirdanswer")
    private String thirdanswer;

    @Column(name = "answer")
    private String answer;
    @Column(name = "question")
    private String question;

}
