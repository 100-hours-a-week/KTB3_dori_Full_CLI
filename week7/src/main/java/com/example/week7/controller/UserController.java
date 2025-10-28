package com.example.week7.controller;

import com.example.week7.common.response.APIResponse;
import com.example.week7.dto.request.user.ChangePasswordDto;
import com.example.week7.dto.request.user.UserSignUpDto;
import com.example.week7.dto.request.user.UserUpdateDto;
import com.example.week7.dto.response.user.SignUpResponse;
import com.example.week7.dto.response.user.UserDetailResponse;
import com.example.week7.service.user.UserCommandService;
import com.example.week7.service.user.UserQueryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    @PostMapping
    public ResponseEntity<APIResponse<SignUpResponse>> register(@Valid @RequestBody UserSignUpDto dto) {
        SignUpResponse registerMember = userCommandService.signUp(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(APIResponse.success("회원가입 성공", registerMember));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<UserDetailResponse>> getUserInfo(@PathVariable Long id) {
        UserDetailResponse userInfo = userQueryService.getUserInfo(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(APIResponse.success("조회 성공", userInfo));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<APIResponse<UserDetailResponse>> userUpdate(@PathVariable Long id,
                                                                      @RequestBody UserUpdateDto dto,
                                                                      HttpServletRequest request) {
        String email = (String) request.getAttribute("email");
        UserDetailResponse user = userCommandService.updateUser(dto, id, email);

        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.success("사용자 정보 업데이트 성공", user));
    }

    @PatchMapping("/{id}/pwd")
    public ResponseEntity<APIResponse<Void>> changePassword(@PathVariable Long id,
                                                            @Valid @RequestBody ChangePasswordDto dto,
                                                            HttpServletRequest request) {

        String email = (String) request.getAttribute("email");
        userCommandService.changePassword(dto, id, email);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userCommandService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
