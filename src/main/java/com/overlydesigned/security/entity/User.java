package com.overlydesigned.security.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "User")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "account_Expired")
    private boolean accountExpired;

    @Column(name = "roles")
    private String roles;

    @Column(name = "account_Locked")
    private boolean accountLocked;

    @Column(name = "credentials_Expired")
    private boolean credentialsExpired;

    @Column(name = "enabled")
    private boolean enabled;

}
