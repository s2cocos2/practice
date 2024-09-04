package com.study.memberservice.service;

import com.study.memberservice.dto.request.LoginRequestDto;
import com.study.memberservice.entity.RefreshToken;
import com.study.memberservice.entity.Member;
import com.study.memberservice.exception.CommonException;
import com.study.memberservice.repository.RefreshTokenRepository;
import com.study.memberservice.repository.UserRepository;
import com.study.memberservice.response.CommonResponse;
import com.study.memberservice.type.UserRoleEnum;
import com.study.memberservice.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import static com.study.memberservice.exception.errorcode.ClientErrorCode.*;
import static com.study.memberservice.response.SuccessMessage.*;
import static com.study.memberservice.util.JwtUtil.*;


@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    @Transactional
    public CommonResponse login(LoginRequestDto requestDto, HttpServletResponse response){
        Member member = userRepository.findByIdentify(requestDto.identify()).orElseThrow(() ->
                new CommonException(NO_ACCOUNT));
        if(!passwordEncoder.matches(requestDto.password(), member.getPassword())){
            throw new CommonException(INVALID_PASSWORDS);
        }

        String accessToken = jwtUtil.createAccessToken(member.getMemberId(), member.getRole());
        String refreshToken = jwtUtil.createRefreshToken(member.getMemberId());

        refreshTokenRepository.deleteByUserId(member.getMemberId());
        RefreshToken refreshTokenEntity = new RefreshToken(refreshToken.substring(7), member.getMemberId());
        refreshTokenRepository.save(refreshTokenEntity);

        response.addHeader(AUTHORIZATION_ACCESS, accessToken);
        response.addHeader(AUTHORIZATION_REFRESH, refreshToken);

        return new CommonResponse(LOGIN_SUCCESS);
    }


    @Transactional(readOnly = true)
    public CommonResponse reissueToken(HttpServletRequest request, HttpServletResponse response) {

        String refreshToken = jwtUtil.getJwtFromHeader(request, AUTHORIZATION_REFRESH);

        if (StringUtils.hasText(refreshToken)) {
            if (jwtUtil.validateRefreshToken(refreshToken)) {
                refreshTokenRepository.findByRefreshToken(refreshToken).orElseThrow(
                        ()-> new CommonException(NO_REFRESHTOKEN));

                Long userId = jwtUtil.getUserInfoFromRefreshToken(refreshToken);

                String newAccessToken = jwtUtil.createAccessToken(userId, UserRoleEnum.USER);

                response.addHeader(AUTHORIZATION_ACCESS, newAccessToken);
                return new CommonResponse(CREATE_REFRESHTOKEN);
            }
        }
        throw new CommonException(INVALID_REFRESHTOKEN);
    }
}
