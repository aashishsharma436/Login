package com.practice.login.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;


@Entity
@Table(name = "user_organization")
@Data
public class UserOrganization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orgUserId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "org_id", nullable = false)
    private Organization organization;

    private String designation;

    private BigDecimal salary;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private User manager;

}
