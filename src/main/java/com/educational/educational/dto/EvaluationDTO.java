package com.educational.educational.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class EvaluationDTO {
    private Integer course;
    private String start;
    private String end;
    private String firstanswer;
    private String secondanswer;
    private String thirdanswer;
    private String answer;
    private String question;
}
