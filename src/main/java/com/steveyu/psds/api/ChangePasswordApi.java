package com.steveyu.psds.api;

import com.steveyu.psds.dto.ChangePasswordForm;
import com.steveyu.psds.dto.User;
import com.steveyu.psds.entity.Admin;
import com.steveyu.psds.entity.Root;
import com.steveyu.psds.entity.Student;
import com.steveyu.psds.entity.Teacher;
import com.steveyu.psds.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/changePassword")
public class ChangePasswordApi {
    @Autowired
    StudentService studentService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    AdminService adminService;

    @Autowired
    RootService rootService;

    @Autowired
    LoginService loginService;


    @PostMapping
    public String changePassword(@RequestBody ChangePasswordForm changePasswordForm) {
        if(changePasswordForm.getType().equals("student")) {
            Optional<Student> studentOptional = studentService.findStudentById(changePasswordForm.getUserId());
            if(studentOptional.isEmpty()) return "用户不存在";
            else {
                Student student = studentOptional.get();
                if(student.getPassword().equals(DigestUtils.md5DigestAsHex(changePasswordForm.getOldPassword().getBytes()))) {
                    student.setPassword(DigestUtils.md5DigestAsHex(changePasswordForm.getNewPassword().getBytes()));
                    studentService.saveStudent(student);
                    return "密码修改成功";
                } else return "原密码错误";
            }
        } else if(changePasswordForm.getType().equals("teacher")) {
            Optional<Teacher> teacherOptional = teacherService.findTeacherById(changePasswordForm.getUserId());
            if(teacherOptional.isEmpty()) return "用户不存在";
            else {
                Teacher teacher = teacherOptional.get();
                if(teacher.getPassword().equals(DigestUtils.md5DigestAsHex(changePasswordForm.getOldPassword().getBytes()))) {
                    teacher.setPassword(DigestUtils.md5DigestAsHex(changePasswordForm.getNewPassword().getBytes()));
                    teacherService.saveTeacher(teacher);
                    return "密码修改成功";
                } else return "原密码错误";
            }
        } else if(changePasswordForm.getType().equals("admin")) {
            Optional<Admin> adminOptional = adminService.findAdminById(changePasswordForm.getUserId());
            if(adminOptional.isEmpty()) return "用户不存在";
            else {
                Admin admin = adminOptional.get();
                if(admin.getPassword().equals(DigestUtils.md5DigestAsHex(changePasswordForm.getOldPassword().getBytes()))) {
                    admin.setPassword(DigestUtils.md5DigestAsHex(changePasswordForm.getNewPassword().getBytes()));
                    adminService.saveAdmin(admin);
                    return "密码修改成功";
                } else return "原密码错误";
            }
        } else if(changePasswordForm.getType().equals("root")) {
            Optional<Root> rootOptional = rootService.findRootById(changePasswordForm.getUserId());
            if(rootOptional.isEmpty()) return "用户不存在";
            else {
                Root root = rootOptional.get();
                if(root.getPassword().equals(DigestUtils.md5DigestAsHex(changePasswordForm.getOldPassword().getBytes()))) {
                    root.setPassword(DigestUtils.md5DigestAsHex(changePasswordForm.getNewPassword().getBytes()));
                    rootService.saveRoot(root);
                    return "密码修改成功";
                } else return "原密码错误";
            }
        } else return "type类型错误";
    }
}
