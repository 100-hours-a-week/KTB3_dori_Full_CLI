package com.example.week7.repository.token;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository {

    void save(String email, String token);

    Optional<String> findTokenByEmail(String email);

    void delete(String email);
}
