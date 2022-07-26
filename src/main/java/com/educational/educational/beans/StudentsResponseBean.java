package com.educational.educational.beans;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class StudentsResponseBean {

    @Getter
    @Setter
    private ResponseBean API;

    @Getter @Setter
    private List<StudentBean> result;

}
