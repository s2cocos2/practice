package com.study.memberservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long refreshTokenId;

    @Column(nullable = false)
    private String refreshToken;

    @Column(nullable = false)
    private Long memberId;

    public RefreshToken(String refreshToken, Long memberId){
        this.refreshToken = refreshToken;
        this.memberId = memberId;
    }
}