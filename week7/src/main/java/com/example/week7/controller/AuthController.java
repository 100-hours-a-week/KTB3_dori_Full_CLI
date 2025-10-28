package com.example.week7.controller;

import com.example.week7.common.exception.custom.UnauthorizedException;
import com.example.week7.common.response.APIResponse;
import com.example.week7.dto.request.user.UserLoginDto;
import com.example.week7.dto.response.user.LoginResponse;
import com.example.week7.service.auth.AuthServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.week7.common.exception.ErrorMessage.*;

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

    @PostMapping("/refresh")
    public ResponseEntity<APIResponse<LoginResponse>> validateRefreshToken(@RequestParam String token) {
        LoginResponse response = authService.validateRefreshToken(token);
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.success("토큰 재발급 성공", response));
    }

    @PostMapping("/session")
    public ResponseEntity<APIResponse<Void>> logout(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            throw new UnauthorizedException(UNAUTHORIZED);
        }
        String[] token = header.split(" ");
        authService.logout(token[1]);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
