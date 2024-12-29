package com.practice.login.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_roles")
@Data
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(unique = true, nullable = false)
    private String roleName;
}
