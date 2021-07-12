package com.jfl.service.r2dbc.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Getter
@Setter
@ToString
@Table("user")
public class User {

    @Id
    private Long id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String mobileNo;
    @Column
    private String roleAlias;
    @Column
    private Boolean active;
    @Transient
    private List<Role> roles;
    //private User parent;
    //private List<User> childs;
}
