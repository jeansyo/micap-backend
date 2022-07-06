package com.educational.educational.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "courses")
public class Courses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column( name = "id" )
    private Integer id;

    @Getter @Setter @Column(name = "name")
    private String name;

    @Getter @Setter @Column(name="user")
    private Integer user;

    @Getter @Setter @Column(name = "status")
    private Integer status = 1;

    @Getter @Setter @Column(name = "classname")
    private String classname;

    @Getter @Setter @Column(name = "recommended")
    private String recommended;

}
