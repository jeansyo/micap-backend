package com.educational.educational.beans;

import com.educational.educational.models.Materials;
import lombok.Getter;
import lombok.Setter;

public class MaterialResponseBean {

    @Getter
    @Setter
    private ResponseBean API;

    @Getter @Setter
    private MaterialBean result;
}
