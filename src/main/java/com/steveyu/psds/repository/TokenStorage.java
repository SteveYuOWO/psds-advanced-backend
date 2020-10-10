package com.steveyu.psds.repository;

import com.steveyu.psds.dto.Token;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Optional;

/**
 * token storage
 */
@Repository
public class TokenStorage implements TokenRepository {
    static HashMap<String, Token> tokenStorage = new HashMap<>();

    @Override
    public Optional<Token> getToken(String tokenStr) {
        return tokenStorage.get(tokenStr) == null ? Optional.empty() : Optional.of(tokenStorage.get(tokenStr));
    }

    @Override
    public void saveToken(Token token) {
        tokenStorage.put(token.getTokenMsg(), token);
    }
}
