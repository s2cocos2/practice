package com.study.memberservice.repository;

import com.study.memberservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByNickname(String nickName);
    Optional<User> findByUserId(Long userId);
    Optional<User> findByIdentify(String identify);
}