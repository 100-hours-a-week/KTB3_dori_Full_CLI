package com.example.week7.service.user;

import com.example.week7.dto.response.user.UserDetailResponse;

public interface UserQueryService {
    UserDetailResponse getUserInfo(Long id);
}
