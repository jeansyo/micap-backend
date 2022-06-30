package com.educational.educational.beans;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class MaterialsRecentResponseBean {

    @Getter @Setter
    private ResponseBean API;

    @Getter @Setter
    private List<MaterialRecentBean> result;

}
