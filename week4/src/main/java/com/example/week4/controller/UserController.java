package com.example.week4.controller;

import com.example.week4.common.response.APIResponse;
import com.example.week4.dto.request.user.UserSignUpDto;
import com.example.week4.dto.response.user.SignUpResponse;
import com.example.week4.dto.response.user.UserDetailResponse;
import com.example.week4.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<APIResponse<SignUpResponse>> register(@Valid @RequestBody UserSignUpDto dto) {
        SignUpResponse registerMember = userService.signUp(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(APIResponse.success("회원가입 성공", registerMember));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<APIResponse<UserDetailResponse>> getUserInfo(@PathVariable Long id) {
        UserDetailResponse userInfo = userService.getUserInfo(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(APIResponse.success("조회 성공", userInfo));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
