package com.example.week5.service.auth;

import com.example.week5.dto.response.user.LoginResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    LoginResponse login(String email, String password);

    LoginResponse validateRefreshToken(String token);

    void logout(String token);
}
