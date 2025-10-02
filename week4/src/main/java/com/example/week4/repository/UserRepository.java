package com.example.week4.repository;

import com.example.week4.domain.User;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Map;

@Repository
public class UserRepository {
    private static final Map<Long, User> memberMap = new LinkedHashMap<>();
    private static long userCount = 0L;

    public User save(User user) {
        user.generateId(++userCount);
        memberMap.put(user.getId(), user);
        return user;
    }

}
