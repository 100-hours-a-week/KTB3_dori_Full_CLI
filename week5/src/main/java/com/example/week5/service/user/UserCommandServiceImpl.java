package com.example.week5.service.user;

import com.example.week5.common.AuthValidator;
import com.example.week5.common.exception.custom.*;
import com.example.week5.domain.User;
import com.example.week5.dto.request.user.ChangePasswordDto;
import com.example.week5.dto.request.user.UserSignUpDto;
import com.example.week5.dto.request.user.UserUpdateDto;
import com.example.week5.dto.response.user.SignUpResponse;
import com.example.week5.dto.response.user.UserDetailResponse;
import com.example.week5.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.week5.common.exception.ErrorMessage.*;


@Service
@RequiredArgsConstructor
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
    public UserDetailResponse updateUser(UserUpdateDto dto, Long id, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UnauthorizedException(UNAUTHORIZED)
        );

        User target = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(RESOURCE_NOT_FOUND)
        );

        authValidator.validate(user, target);

        target.update(dto.getNickname(), dto.getProfileImage());

        return UserDetailResponse.fromEntity(target);
    }

    @Override
    public void changePassword(ChangePasswordDto dto, Long id, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UnauthorizedException(UNAUTHORIZED)
        );

        User target = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(RESOURCE_NOT_FOUND)
        );

        if (!user.getId().equals(target.getId())) {
            throw new ForbiddenException(FORBIDDEN);
        }

        authValidator.checkPassword(dto.getPassword(), dto.getPasswordCheck());
        target.changePassword(passwordEncoder.encode(dto.getPassword()));
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }
}
