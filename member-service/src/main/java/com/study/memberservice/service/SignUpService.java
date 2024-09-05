package com.study.memberservice.service;

import com.study.memberservice.dto.request.CheckEmailRequestDto;
import com.study.memberservice.dto.request.CheckNicknameRequestDto;
import com.study.memberservice.dto.request.SendEmailRequestDto;
import com.study.memberservice.dto.request.SignupRequestDto;
import com.study.memberservice.entity.Email;
import com.study.memberservice.entity.Member;
import com.study.memberservice.exception.CommonException;
import com.study.memberservice.repository.EmailRepository;
import com.study.memberservice.repository.MemberRepository;
import com.study.memberservice.response.CommonResponse;
import com.study.memberservice.type.UserRoleEnum;
import com.study.memberservice.util.EmailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.study.memberservice.exception.errorcode.ClientErrorCode.*;
import static com.study.memberservice.response.SuccessMessage.*;

@Service
@RequiredArgsConstructor
public class SignUpService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailRepository emailRepository;
    private final EmailUtil emailUtil;


    // ADMIN_TOKEN
    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    public CommonResponse signup(SignupRequestDto requestDto) {
        String email = passwordEncoder.encode(requestDto.getEmail());
        String name = passwordEncoder.encode(requestDto.getName());
        String phoneNumber = requestDto.getPhoneNumber();
        String id = requestDto.getIdentify();
        String nickname = requestDto.getNickname();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String address = passwordEncoder.encode(requestDto.getAddress());

        if(existingNickname(nickname)){
            throw new CommonException(DUPLICATE_NICKNAME);
        }

        long emailId = checkEmailSuccessKey(requestDto.getEmail(), requestDto.getSuccessKey());
        emailRepository.deleteById(emailId);

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new CommonException(INVALID_ADMIN_TOKEN);
            }
            role = UserRoleEnum.ADMIN;
        }

        Member member = new Member(email, name, phoneNumber, id, nickname, password, address, role);
        Member savedMember =  memberRepository.save(member);
        if(savedMember == null){
            throw new CommonException(SIGNUP_FAILED);
        }
        return new CommonResponse(SIGNUP_SUCCESS);
    }


    public CommonResponse checkNickname(CheckNicknameRequestDto requestDto) {
        String nickname = requestDto.nickname();
        if (existingNickname(nickname)) {
            throw new CommonException(DUPLICATE_NICKNAME);
        }
        return new CommonResponse(AVAILABLE_NICKNAME);
    }


    public boolean existingNickname(String nickname){
        Optional<Member> existingNickname = memberRepository.findByNickname(nickname);
        if(existingNickname.isPresent()){
            return true;
        } else {
            return false;
        }
    }

    public CommonResponse sendEmail(SendEmailRequestDto requestDto) {

        memberRepository.findByEmail(requestDto.email()).ifPresent(existingMember -> {
            throw new CommonException(DUPLICATE_EMAIL);
        });

        Optional<Email> email = emailRepository.findByEmail(requestDto.email());
        CommonResponse response = new CommonResponse(SEND_EMAIL_CODE);
        String successKey = emailUtil.makeRandomNumber();

        try{
            emailUtil.sendEmail(requestDto.email(), successKey);
        } catch (CommonException e) {
            throw new CommonException(EMAIL_SENDING_FAILED);
        }
        if (email.isEmpty()) {
            emailRepository.save(Email.builder()
                    .email(requestDto.email())
                    .successKey(successKey)
                    .build());
            return response;
        }
        email.get().changeSuccessKey(successKey);
        return response;
    }


    public CommonResponse checkEmail(CheckEmailRequestDto requestDto) {
        checkEmailSuccessKey(requestDto.email(), requestDto.successKey());
        return new CommonResponse(EMAIL_AUTHENTICATE_SUCCESS);
    }

    public long checkEmailSuccessKey(String requestEmail, String successKey) {
        Email email = emailRepository.findByEmail(requestEmail).orElseThrow(
                ()-> new CommonException(NO_ACCOUNT));
        if(!email.getSuccessKey().equals(successKey)){
            throw new CommonException(EMAIL_AUTHENTICATION_FAILED);
        }
        return email.getId();
    }
}