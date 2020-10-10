package com.steveyu.psds.service;

import com.steveyu.psds.dto.HttpResponse;
import com.steveyu.psds.dto.Token;
import com.steveyu.psds.dto.User;
import com.steveyu.psds.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    RootRepository rootRepository;

    @Autowired
    TokenRepository tokenRepository;

    public HttpResponse login(User user) {
        if (user.getLoginType().equals("student")) {
            var studentOptional = studentRepository.findByStuNum(user.getUsername());
            if (studentOptional.isEmpty()) {
                return new HttpResponse("用户不存在", null);
            } else {
                var student = studentOptional.get();
                if (student.getPassword().equals(user.getPassword())) {
                    var token = new Token(student);
                    tokenRepository.saveToken(token);
                    return new HttpResponse("登录成功", token);
                }
                return new HttpResponse("密码输入错误", null);
            }
        } else if (user.getLoginType().equals("teacher")) {
            var teacherOptional = teacherRepository.findByTeaNum(user.getUsername());
            if (teacherOptional.isEmpty()) {
                return new HttpResponse("用户不存在", null);
            } else {
                var teacher = teacherOptional.get();
                if (teacher.getPassword().equals(user.getPassword())) {
                    var token = new Token(teacher);
                    tokenRepository.saveToken(token);
                    return new HttpResponse("登录成功", token);
                }
                return new HttpResponse("密码错误", null);
            }
        } else if (user.getLoginType().equals("admin")) {
            var adminOptional = adminRepository.findByName(user.getUsername());
            if (adminOptional.isEmpty()) {
                user.setLoginType("root");
                return login(user);
            } else {
                var admin = adminOptional.get();
                if (admin.getPassword().equals(user.getPassword())) {
                    var token = new Token(admin);
                    tokenRepository.saveToken(token);
                    return new HttpResponse("登录成功", token);
                }
                return new HttpResponse("密码错误", null);
            }
        } else if (user.getLoginType().equals("root")) {
            var rootOptional = rootRepository.findByName(user.getUsername());
            if (rootOptional.isEmpty()) {
                return new HttpResponse("用户不存在", null);
            } else {
                var root = rootOptional.get();
                if (root.getPassword().equals(user.getPassword())) {
                    var token = new Token(root);
                    tokenRepository.saveToken(token);
                    return new HttpResponse("登录成功", token);
                }
                return new HttpResponse("密码错误", null);
            }
        } else {
            return new HttpResponse("登录类型错误", null);
        }
    }
}
