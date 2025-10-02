package com.example.week4.service;

import com.example.week4.domain.User;
import com.example.week4.dto.request.user.UserLoginDto;
import com.example.week4.dto.request.user.UserSignUpDto;
import com.example.week4.dto.response.user.SignUpResponse;
import com.example.week4.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public SignUpResponse signUp(UserSignUpDto dto) {
        //isExistEmail(dto.getEmail());
        //isExistNickname(dto.getNickname());
//        checkPassword(dto.getPassword(), dto.getPasswordCheck());
        User user = UserSignUpDto.toEntity(dto);
        User saveUser = userRepository.save(user);
        return SignUpResponse.fromEntity(saveUser);
    }


}
