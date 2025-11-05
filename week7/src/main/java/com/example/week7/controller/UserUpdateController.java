package com.example.week7.controller;

import com.example.week7.common.annotation.AuthUser;
import com.example.week7.common.response.APIResponse;
import com.example.week7.dto.response.user.UserDetailResponse;
import com.example.week7.service.user.UserCommandService;
import com.example.week7.service.user.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/my")
@RequiredArgsConstructor
public class UserUpdateController {

    private final UserQueryService userQueryService;
    private final UserCommandService userCommandService;

    @GetMapping
    public ResponseEntity<APIResponse<UserDetailResponse>> getUserInfo(@AuthUser String email) {
        UserDetailResponse user = userQueryService.getUserInfoByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.success("사용자 조회 성공", user));
    }

}
