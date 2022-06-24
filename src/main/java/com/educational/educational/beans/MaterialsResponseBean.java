package com.educational.educational.beans;

import com.educational.educational.models.Materials;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class MaterialsResponseBean {

    @Getter @Setter
    private ResponseBean API;

    @Getter @Setter
    private List<Materials> result;

}
