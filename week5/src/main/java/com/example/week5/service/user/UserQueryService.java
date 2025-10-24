package com.example.week5.service.user;

import com.example.week5.dto.response.user.UserDetailResponse;

public interface UserQueryService {
    UserDetailResponse getUserInfo(Long id);
}
