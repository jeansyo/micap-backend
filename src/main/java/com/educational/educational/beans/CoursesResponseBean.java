package com.educational.educational.beans;

import com.educational.educational.models.Courses;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class CoursesResponseBean {

    @Getter @Setter
    private ResponseBean API;

    @Getter @Setter
    private List<Courses> result;

}
