package com.educational.educational.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseResponseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter @Setter @JsonProperty("id")
    private Integer id;

    @Getter @Setter @JsonProperty("name")
    private String name;

}
