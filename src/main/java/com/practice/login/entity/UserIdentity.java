package com.practice.login.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_identity")
@Data
public class UserIdentity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long identityId;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(unique = true)
    private String aadharNumber;

    @Column(unique = true)
    private String panCard;
}


