package com.study.memberservice.entity;

import com.study.memberservice.type.UserRoleEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long memberId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String identify;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;


//    @OneToMany(mappedBy = "user", orphanRemoval = true)
//    private List<Post> postList = new ArrayList<>();


    public Member(String email, String name, String phoneNumber, String identify,
                  String nickname, String password, String address, UserRoleEnum role) {
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.identify = identify;
        this.nickname = nickname;
        this.password = password;
        this.address = address;
        this.role = role;
    }
}
