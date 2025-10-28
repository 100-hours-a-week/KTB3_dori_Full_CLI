package com.example.week7.service.auth;

import com.example.week7.common.exception.custom.UnauthorizedException;
import com.example.week7.common.jwt.JwtUtil;
import com.example.week7.domain.User;
import com.example.week7.dto.response.user.LoginResponse;
import com.example.week7.repository.token.RefreshTokenRepository;
import com.example.week7.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.week7.common.exception.ErrorMessage.*;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public LoginResponse login(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UnauthorizedException(INVALID_CREDENTIALS)
        );
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new UnauthorizedException(INVALID_CREDENTIALS);
        }
        String accessToken = jwtUtil.createAccessToken(user);
        String refreshToken = jwtUtil.createRefreshToken(user);
        refreshTokenRepository.save(user.getEmail(), refreshToken);
        return LoginResponse.fromEntity(user, accessToken, refreshToken);
    }

    @Override
    public LoginResponse validateRefreshToken(String token) {

        if (!jwtUtil.validateToken(token)) {
            throw new UnauthorizedException(TOKEN_EXPIRE);
        }

        String email = jwtUtil.getEmail(token);
        if (email == null) {
            throw new UnauthorizedException(UNAUTHORIZED);
        }

        String refreshToken = refreshTokenRepository.findTokenByEmail(email).orElseThrow(
                () ->  new UnauthorizedException(TOKEN_EXPIRE)
        );


        if (!refreshToken.equals(token)) {
            throw new UnauthorizedException(UNAUTHORIZED);
        }

        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UnauthorizedException(UNAUTHORIZED)
        );

        String accessToken = jwtUtil.createAccessToken(user);
        return LoginResponse.fromEntity(user, accessToken, refreshToken);
    }

    @Override
    public void logout(String token) {
        String email = jwtUtil.getEmail(token);

        if (email == null) {
            throw new UnauthorizedException(UNAUTHORIZED);
        }

        refreshTokenRepository.delete(email);
    }
}
