package com.educational.educational.models;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Random;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter @Setter @Column(name="id")
    private Integer id;
    @Getter @Setter @Column(name="code")
    private String code = "CODUSR-" + String.format("%06d", new Random().nextInt(999999));
    @Getter @Setter @Column(name="name")
    private String name;
    @Getter @Setter @Column(name="email")
    private String email;
    @Getter @Setter @Column(name="password")
    private String password;
    @Getter @Setter @Column(name="role")
    private Integer role;
    @Getter @Setter @Column(name="status")
    private Integer status = 1;

}
