package com.example.week5.service;

import com.example.week5.common.exception.custom.UnauthorizedException;
import com.example.week5.common.jwt.JwtUtil;
import com.example.week5.domain.User;
import com.example.week5.dto.response.user.LoginResponse;
import com.example.week5.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public LoginResponse login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UnauthorizedException("이메일과 비밀번호를 확인해주세요"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new UnauthorizedException("이메일과 비밀번호를 확인해주세요");
        }
        String token = jwtUtil.createAccessToken(user);
        return LoginResponse.fromEntity(user, token);
    }
}
