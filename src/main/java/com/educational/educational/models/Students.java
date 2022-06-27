package com.educational.educational.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "students")
public class Students {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column( name = "id" )
    private Integer id;

    @Getter @Setter @Column( name = "user" )
    private Integer user;

    @Getter @Setter @Column( name = "course" )
    private Integer course;

}
