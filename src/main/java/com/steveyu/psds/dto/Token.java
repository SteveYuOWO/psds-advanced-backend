package com.steveyu.psds.dto;

import com.steveyu.psds.repository.TokenRepository;
import com.steveyu.psds.repository.TokenStorage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Token {
    String tokenMsg;
    Object user;

    public Token(Object user) {
        this.tokenMsg = UUID.randomUUID().toString();
        this.user = user;
    }
}
