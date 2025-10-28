package com.example.week7.service.user;

import com.example.week7.common.exception.custom.ResourceNotFoundException;
import com.example.week7.domain.User;
import com.example.week7.dto.response.user.UserDetailResponse;
import com.example.week7.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.week7.common.exception.ErrorMessage.RESOURCE_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserQueryServiceImpl implements UserQueryService {
    private final UserRepository userRepository;

    @Override
    public UserDetailResponse getUserInfo(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND));
        return UserDetailResponse.fromEntity(user);
    }
}
