package com.practice.login.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "organizations")
@Data
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orgId;

    @Column(unique = true, nullable = false)
    private String orgName;

    private String orgAddress;

    private LocalDateTime createdAt = LocalDateTime.now();

    // Getters and Setters
}
