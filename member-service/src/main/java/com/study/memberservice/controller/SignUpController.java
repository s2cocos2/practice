package com.study.memberservice.controller;

import com.study.memberservice.dto.request.CheckEmailRequestDto;
import com.study.memberservice.dto.request.CheckNicknameRequestDto;
import com.study.memberservice.dto.request.SendEmailRequestDto;
import com.study.memberservice.dto.request.SignupRequestDto;
import com.study.memberservice.response.CommonResponse;
import com.study.memberservice.service.SignUpService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class SignUpController {

    private final SignUpService signUpService;

    @PostMapping("/signup")
    public ResponseEntity<CommonResponse> signup(@RequestBody @Valid SignupRequestDto requestDto){
        return new ResponseEntity<>(signUpService.signup(requestDto), HttpStatus.CREATED);
    }

    @PostMapping("/signup/nicknameCheck")
    public ResponseEntity<CommonResponse> checkNickname(@RequestBody @Valid CheckNicknameRequestDto requestDto){
        return new ResponseEntity<>(signUpService.checkNickname(requestDto), HttpStatus.CREATED);
    }

    @PostMapping("/signup/emailSend")
    public ResponseEntity<CommonResponse> sendEmail(@RequestBody @Valid SendEmailRequestDto requestDto){
        return new ResponseEntity<>(signUpService.sendEmail(requestDto), HttpStatus.CREATED);
    }

    @PostMapping("/signup/emailCheck")
    public ResponseEntity<CommonResponse> checkEmail(@RequestBody @Valid CheckEmailRequestDto requestDto){
        return new ResponseEntity<>(signUpService.checkEmail(requestDto), HttpStatus.CREATED);
    }
}

