package com.steveyu.psds.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/testApi")
public class TestApi {
    @GetMapping
    public String test() {
        return "start success";
    }
}
