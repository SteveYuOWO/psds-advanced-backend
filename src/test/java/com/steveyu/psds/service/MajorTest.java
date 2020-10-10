package com.steveyu.psds.service;

import com.steveyu.psds.entity.Major;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class MajorTest {
    @Autowired
    MajorService majorService;

    @Test
    public void testFindMajorByName() {
    }
}
