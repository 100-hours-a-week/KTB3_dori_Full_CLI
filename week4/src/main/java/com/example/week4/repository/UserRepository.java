package com.example.week4.repository;

import com.example.week4.domain.User;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository {
    private static final Map<Long, User> userMap = new LinkedHashMap<>();
    private static long userCount = 0L;

    public User save(User user) {
        user.generateId(++userCount);
        userMap.put(user.getId(), user);
        return user;
    }

    public Optional<User> findById(Long id) {
        return Optional.ofNullable(userMap.get(id));
    }

    public Optional<User> findByEmail(String email) {
        for (User user : userMap.values()) {
            if (user.getEmail().equals(email)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public Optional<User> findByNickname(String nickname) {
        for (User user : userMap.values()) {
            if (user.getNickname().equals(nickname)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public void delete(Long id) {
        userMap.remove(id);
    }

}
