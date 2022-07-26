package com.educational.educational.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "downloads")
public class Downloads {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user")
    private Integer user;

    @Column(name = "course")
    private Integer course;

    @Column(name = "material")
    private Integer material;

}
