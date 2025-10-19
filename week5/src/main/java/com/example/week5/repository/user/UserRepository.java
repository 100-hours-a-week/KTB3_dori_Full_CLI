package com.example.week5.repository.user;

import com.example.week5.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository {

    public User save(User user);

    public Optional<User> findById(Long id);

    public Optional<User> findByEmail(String email);

    public Optional<User> findByNickname(String nickname);

    public void delete(Long id);
}
