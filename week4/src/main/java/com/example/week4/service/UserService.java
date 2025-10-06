package com.example.week4.service;

import com.example.week4.common.exception.custom.BadRequestException;
import com.example.week4.common.exception.custom.UserDuplicatedException;
import com.example.week4.domain.User;
import com.example.week4.dto.request.user.UserLoginDto;
import com.example.week4.dto.request.user.UserSignUpDto;
import com.example.week4.dto.response.user.LoginResponse;
import com.example.week4.dto.response.user.SignUpResponse;
import com.example.week4.dto.response.user.UserDetailResponse;
import com.example.week4.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

/*    public LoginResponse login(UserLoginDto dto) {
        return LoginResponse
    }*/

    public SignUpResponse signUp(UserSignUpDto dto) {
        isExistEmail(dto.getEmail());
        isExistNickname(dto.getNickname());
//        checkPassword(dto.getPassword(), dto.getPasswordCheck());

        User user = UserSignUpDto.toEntity(dto);
        User saveUser = userRepository.save(user);
        return SignUpResponse.fromEntity(saveUser);
    }

    public UserDetailResponse getUserInfo(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new BadRequestException("잘못된 접근입니다"));
        return UserDetailResponse.fromEntity(user);
    }

    public void delete(Long id) {
        userRepository.delete(id);
    }


    private void isExistEmail(String email) {
        if(userRepository.findByEmail(email).isPresent()) {
            throw new UserDuplicatedException("중복된 이메일입니다");
        }
    }

    private void isExistNickname(String nickname) {
        if (userRepository.findByNickname(nickname).isPresent()) {
            throw new UserDuplicatedException("중복된 닉네임 입니다");
        }
    }


}
