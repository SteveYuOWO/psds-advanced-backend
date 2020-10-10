package com.steveyu.psds.api;

import com.steveyu.psds.dto.HttpResponse;
import com.steveyu.psds.dto.User;
import com.steveyu.psds.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/validate")
public class ValidateApi {
    @Autowired
    LoginService loginService;

    Logger logger = LoggerFactory.getLogger(ValidateApi.class);

    @PostMapping("login")
    public HttpResponse login(@Validated @RequestBody User user) {
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        logger.info("username: " + user.getUsername() + " password: " + user.getPassword());
        return loginService.login(user);
    }
}
