package com.practice.login.repository;

import com.practice.login.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface UserRepository extends JpaRepository<User,String> {
    boolean existsByUsername(String username);
    boolean existsByWorkEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "Update users set IS_EMAIL_VERIFIED = 'true', UPDATED_AT = ? where EMAIL = ?",nativeQuery = true)
    int verifyEmail(LocalDateTime updateAt,String email);

    @Modifying
    @Transactional
    @Query(value = "Update users set IS_MOBILE_VERIFIED = 'true', UPDATED_AT = ? where MOBILE = ?",nativeQuery = true)
    int verifyMobile(LocalDateTime updateAt,String mobile);

    @Query(value = "SELECT * FROM USERS where USERNAME = ?1 OR  WORK_EMAIL=?1 ",nativeQuery = true)
    User getPassword(String username);
}
