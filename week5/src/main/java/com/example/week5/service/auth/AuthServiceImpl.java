package com.example.week5.service.auth;

import com.example.week5.common.exception.custom.UnauthorizedException;
import com.example.week5.common.jwt.JwtUtil;
import com.example.week5.domain.User;
import com.example.week5.dto.response.user.LoginResponse;
import com.example.week5.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.week5.common.exception.ErrorMessage.*;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public LoginResponse login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UnauthorizedException(INVALID_CREDENTIALS));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new UnauthorizedException(INVALID_CREDENTIALS);
        }
        String token = jwtUtil.createAccessToken(user);
        return LoginResponse.fromEntity(user, token);
    }
}
