package com.example.week5.service.user;

import com.example.week5.dto.request.user.ChangePasswordDto;
import com.example.week5.dto.request.user.UserSignUpDto;
import com.example.week5.dto.request.user.UserUpdateDto;
import com.example.week5.dto.response.user.SignUpResponse;
import com.example.week5.dto.response.user.UserDetailResponse;

public interface UserCommandService {

    SignUpResponse signUp(UserSignUpDto dto);

    UserDetailResponse getUserInfo(Long id);

    UserDetailResponse updateUser(UserUpdateDto dto, Long id, String email);

    void changePassword(ChangePasswordDto dto, Long id, String email);

    void delete(Long id);
}
