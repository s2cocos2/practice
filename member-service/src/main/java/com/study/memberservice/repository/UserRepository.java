package com.study.memberservice.repository;

import com.study.memberservice.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    Optional<Member> findByNickname(String nickName);
    Optional<Member> findByUserId(Long userId);
    Optional<Member> findByIdentify(String identify);
}