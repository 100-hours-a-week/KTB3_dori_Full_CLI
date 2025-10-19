package com.example.week5.controller;

import com.example.week5.common.response.APIResponse;
import com.example.week5.dto.request.user.UserLoginDto;
import com.example.week5.dto.response.user.LoginResponse;
import com.example.week5.service.auth.AuthServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceImpl authService;

    @PostMapping
    public ResponseEntity<APIResponse<LoginResponse>> login(@Valid @RequestBody UserLoginDto dto) {
        LoginResponse response = authService.login(dto.getEmail(), dto.getPassword());
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.success("로그인 성공", response));
    }
}
