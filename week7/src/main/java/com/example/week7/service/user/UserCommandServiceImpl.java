package com.example.week7.service.user;

import com.example.week7.common.AuthValidator;
import com.example.week7.common.exception.custom.*;
import com.example.week7.domain.User;
import com.example.week7.dto.request.user.ChangePasswordDto;
import com.example.week7.dto.request.user.UserSignUpDto;
import com.example.week7.dto.request.user.UserUpdateDto;
import com.example.week7.dto.response.user.SignUpResponse;
import com.example.week7.dto.response.user.UserDetailResponse;
import com.example.week7.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.week7.common.exception.ErrorMessage.*;


@Service
@RequiredArgsConstructor
@Transactional
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthValidator authValidator;

    @Override
    public SignUpResponse signUp(UserSignUpDto dto) {
        authValidator.isExistEmail(dto.getEmail());
        authValidator.isExistNickname(dto.getNickname());
        authValidator.checkPassword(dto.getPassword(), dto.getPasswordCheck());
        User user = UserSignUpDto.ofEntity(dto);

        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        user.changePassword(encodedPassword);
        User saveUser = userRepository.save(user);
        return SignUpResponse.fromEntity(saveUser);
    }

    @Override
    public UserDetailResponse getUserInfo(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND));
        return UserDetailResponse.fromEntity(user);
    }

    @Override
    public UserDetailResponse updateUser(UserUpdateDto dto, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UnauthorizedException(UNAUTHORIZED)
        );

        user.update(dto.getNickname(), dto.getProfileImage());

        return UserDetailResponse.fromEntity(user);
    }

    @Override
    public void changePassword(ChangePasswordDto dto, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UnauthorizedException(UNAUTHORIZED)
        );

        authValidator.checkPassword(dto.getPassword(), dto.getPasswordCheck());
        user.changePassword(passwordEncoder.encode(dto.getPassword()));
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
