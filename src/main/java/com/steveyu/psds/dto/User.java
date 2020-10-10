package com.steveyu.psds.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @NotBlank
    String loginType;
    @NotBlank
    String username;
    @NotBlank
    String password;
}