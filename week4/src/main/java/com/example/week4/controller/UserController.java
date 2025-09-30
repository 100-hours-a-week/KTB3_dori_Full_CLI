package com.example.week4.controller;

import com.example.week4.dto.request.user.UserSignUpDto;
import com.example.week4.dto.response.user.SignUpResponse;
import com.example.week4.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> register(@RequestBody UserSignUpDto dto) {
        SignUpResponse registerMember = userService.signUp(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registerMember);
    }
}
