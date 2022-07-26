package com.educational.educational.beans;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter @Setter @JsonProperty("codeError")
    private String codeError;
    @Getter @Setter @JsonProperty("msgError")
    private String msgError;
    @Getter @Setter @JsonProperty("date")
    private String date;

}

