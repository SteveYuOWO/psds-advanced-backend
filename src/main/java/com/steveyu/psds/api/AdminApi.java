package com.steveyu.psds.api;

import com.steveyu.psds.dto.PageDto;
import com.steveyu.psds.entity.Admin;
import com.steveyu.psds.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admin")
public class AdminApi {
    @Autowired
    AdminService adminService;

    @GetMapping(path = "{page}/{size}")
    public Page<Admin> getAllAdmins(@PathVariable("page") int page,
                                    @PathVariable("size") int size) {
        return adminService.getAllTeachers(PageRequest.of(page, size));
    }

    @PostMapping
    public String saveAdmin(@RequestBody Admin admin) {
        admin.setPassword(DigestUtils.md5DigestAsHex(admin.getPassword().getBytes()));
        adminService.saveAdmin(admin);
        return "添加成功";
    }

    @DeleteMapping(path = "{id}")
    public String deleteAdmin(@PathVariable("id") Integer id) {
        adminService.deleteAdminById(id);
        return "删除成功";
    }
}
