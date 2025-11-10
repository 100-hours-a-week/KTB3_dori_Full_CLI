package com.example.week7.controller;

import com.example.week7.common.annotation.AuthUser;
import com.example.week7.common.response.APIResponse;
import com.example.week7.dto.request.user.ChangePasswordDto;
import com.example.week7.dto.request.user.UserUpdateDto;
import com.example.week7.dto.response.user.UserDetailResponse;
import com.example.week7.service.user.UserCommandService;
import com.example.week7.service.user.UserQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/my")
@RequiredArgsConstructor
public class MyPageController {

    private final UserQueryService userQueryService;
    private final UserCommandService userCommandService;

    @GetMapping
    public ResponseEntity<APIResponse<UserDetailResponse>> getUserInfo(@AuthUser String email) {
        UserDetailResponse user = userQueryService.getUserInfoByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.success("사용자 조회 성공", user));
    }

    @PatchMapping()
    public ResponseEntity<APIResponse<UserDetailResponse>> userUpdate(@RequestBody UserUpdateDto dto,
                                                                      @AuthUser String email) {

        UserDetailResponse user = userCommandService.updateUser(dto, email);

        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.success("사용자 정보 업데이트 성공", user));
    }

    @PatchMapping("/pwd")
    public ResponseEntity<APIResponse<Void>> changePassword(@Valid @RequestBody ChangePasswordDto dto,
                                                            @AuthUser String email) {

        userCommandService.changePassword(dto, email);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
