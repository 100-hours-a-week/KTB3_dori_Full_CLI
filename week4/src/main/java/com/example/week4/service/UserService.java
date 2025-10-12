package com.example.week4.service;

import com.example.week4.common.exception.custom.*;
import com.example.week4.domain.User;
import com.example.week4.dto.request.user.ChangePasswordDto;
import com.example.week4.dto.request.user.UserSignUpDto;
import com.example.week4.dto.request.user.UserUpdateDto;
import com.example.week4.dto.response.user.SignUpResponse;
import com.example.week4.dto.response.user.UserDetailResponse;
import com.example.week4.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SignUpResponse signUp(UserSignUpDto dto) {
        isExistEmail(dto.getEmail());
        isExistNickname(dto.getNickname());
        checkPassword(dto.getPassword(), dto.getPasswordCheck());
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        dto.setPassword(encodedPassword);

        User user = UserSignUpDto.ofEntity(dto);
        User saveUser = userRepository.save(user);
        return SignUpResponse.fromEntity(saveUser);
    }

    public UserDetailResponse getUserInfo(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new BadRequestException("잘못된 접근입니다"));
        return UserDetailResponse.fromEntity(user);
    }

    public UserDetailResponse updateUser(UserUpdateDto dto, Long id, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UnauthorizedException("로그인 후 이용해주세요")
        );

        User target = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("존재하지 않는 페이지입니다")
        );

        if (!user.getId().equals(target.getId())) {
            throw new UnauthenticatedException("권한이 없습니다");
        }

        target.update(dto.getNickname(), dto.getProfileImage());

        return UserDetailResponse.fromEntity(target);
    }

    public void changePassword(ChangePasswordDto dto, Long id, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UnauthorizedException("로그인 후 이용해주세요")
        );

        User target = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("존재하지 않는 페이지입니다")
        );

        if (!user.getId().equals(target.getId())) {
            throw new UnauthenticatedException("권한이 없습니다");
        }

        checkPassword(dto.getPassword(), dto.getPasswordCheck());
        target.changePassword(passwordEncoder.encode(dto.getPassword()));
    }



    public void delete(Long id) {
        userRepository.delete(id);
    }


    private void isExistEmail(String email) {
        if(userRepository.findByEmail(email).isPresent()) {
            throw new DuplicatedException("중복된 이메일입니다");
        }
    }

    private void isExistNickname(String nickname) {
        if (userRepository.findByNickname(nickname).isPresent()) {
            throw new DuplicatedException("중복된 닉네임 입니다");
        }
    }

    private void checkPassword(String password, String passwordCheck) {
        if (!password.equals(passwordCheck)) {
            throw new BadRequestException("비밀번호가 일치하지 않습니다");
        }
    }
}
