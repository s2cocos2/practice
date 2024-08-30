package com.study.memberservice.controller;

import com.study.memberservice.dto.request.LoginRequestDto;
import com.study.memberservice.response.CommonResponse;
import com.study.memberservice.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<CommonResponse> login(@RequestBody @Valid LoginRequestDto requestDto, HttpServletResponse response){
        return new ResponseEntity<>(loginService.login(requestDto, response), HttpStatus.OK);
    }

    @GetMapping("/login/reissueToken")
    public ResponseEntity<CommonResponse> reissueToken(HttpServletRequest request, HttpServletResponse response) {
        return new ResponseEntity<>(loginService.reissueToken(request, response), HttpStatus.CREATED);
    }
}

