package com.educational.educational.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter @Setter @JsonProperty("name")
    private String name;
    @Getter @Setter @JsonProperty("codUsr")
    private String codUsr;
    @Getter @Setter @JsonProperty("id")
    private Integer id;
    @Getter @Setter @JsonProperty("role")
    private Integer role;
    @Getter @Setter @JsonProperty("email")
    private String email;
    @Getter @Setter @JsonProperty("token")
    private String token;
}
