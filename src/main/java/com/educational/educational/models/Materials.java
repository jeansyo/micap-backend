package com.educational.educational.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "materials")
public class Materials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column( name = "id" )
    private Integer id;

    @Getter @Setter @Column(name = "name")
    private String name;

    @Getter @Setter @Column(name = "filename")
    private String filename;

    @Getter @Setter @Column(name="link")
    private String link = "";

    @Getter @Setter @Column(name="type")
    private Integer type;

    @Getter @Setter @Column(name="course")
    private Integer course;

    @Getter @Setter @Column(name = "status")
    private Integer status = 1;

    @Getter @Setter @Column(name="date")
    private String date = new Date().toString();

}
