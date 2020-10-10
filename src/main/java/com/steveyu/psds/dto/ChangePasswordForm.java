package com.steveyu.psds.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ChangePasswordForm {
    Integer userId;
    String oldPassword;
    String newPassword;
    String type;
}
