package com.educational.educational.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @Getter @Setter @Column(name="id")
    private Integer id;
    @Getter @Setter @Column(name="code")
    private String code;
    @Getter @Setter @Column(name="name")
    private String name;
    @Getter @Setter @Column(name="email")
    private String email;
    @Getter @Setter @Column(name="password")
    private String password;
    @Getter @Setter @Column(name="role")
    private Integer role;
    @Getter @Setter @Column(name="status")
    private Integer status;

}
