package com.example.week5.repository.user;

import com.example.week5.domain.User;
import org.springframework.stereotype.Repository;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserMapRepository implements UserRepository {
    private static final Map<Long, User> userMap = new ConcurrentHashMap<>();
    private static final Map<String, User> emailMap = new ConcurrentHashMap<>();
    private static final Map<String, User> nicknameMap = new ConcurrentHashMap<>();
    private static AtomicLong userCount = new AtomicLong(0);

    @Override
    public User save(User user) {
        user.generateId(userCount.incrementAndGet());
        userMap.put(user.getId(), user);
        emailMap.put(user.getEmail(), user);
        nicknameMap.put(user.getNickname(), user);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(userMap.get(id));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(emailMap.get(email));
    }

    @Override
    public Optional<User> findByNickname(String nickname) {
        return Optional.ofNullable(nicknameMap.get(nickname));
    }

    @Override
    public void delete(Long id) {
        User removeUser = userMap.remove(id);
        if (removeUser != null) {
            emailMap.remove(removeUser.getEmail());
            nicknameMap.remove(removeUser.getNickname());
        }
    }

}
