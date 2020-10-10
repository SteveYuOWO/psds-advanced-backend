package com.steveyu.psds.repository;

import com.steveyu.psds.dto.Token;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface TokenRepository {
    Optional<Token> getToken(String tokenStr);

    void saveToken(Token token);
}
