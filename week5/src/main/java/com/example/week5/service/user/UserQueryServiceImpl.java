package com.example.week5.service.user;

import com.example.week5.common.exception.custom.ResourceNotFoundException;
import com.example.week5.domain.User;
import com.example.week5.dto.response.user.UserDetailResponse;
import com.example.week5.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.week5.common.exception.ErrorMessage.RESOURCE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {
    private final UserRepository userRepository;

    @Override
    public UserDetailResponse getUserInfo(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND));
        return UserDetailResponse.fromEntity(user);
    }
}
