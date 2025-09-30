package com.example.week4.repository;

import com.example.week4.domain.User;
import com.example.week4.dto.request.user.UserSignUpDto;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {
    private static final Map<Long, User> memberMap = new HashMap<>();
    private static long userCount = 0L;

    public User save(UserSignUpDto dto) {
        User user = UserSignUpDto.toEntity(dto);
        user.setId(++userCount);
        memberMap.put(user.getId(), user);
        return user;
    }
}
