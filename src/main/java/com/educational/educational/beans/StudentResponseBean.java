package com.educational.educational.beans;

import com.educational.educational.models.Users;
import lombok.Getter;
import lombok.Setter;

public class StudentResponseBean {

    @Getter
    @Setter
    private ResponseBean API;

    @Getter @Setter
    private StudentBean result;
}
