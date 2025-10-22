package com.example.week5.repository.token;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class RefreshTokenMapRepository implements RefreshTokenRepository {
    private static final Map<String, String> tokenMap = new ConcurrentHashMap<>();

    @Override
    public void save(String email, String token) {
        tokenMap.put(email, token);
    }

    @Override
    public Optional<String> findTokenByEmail(String email) {
        return Optional.ofNullable(tokenMap.get(email));
    }

    @Override
    public void delete(String email) {
        tokenMap.remove(email);
    }
}
