package com.educational.educational.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class QuestionDTO {
    private String firstanswer;
    private String secondanswer;
    private String thirdanswer;

    private String question;
    private Integer id;
}
