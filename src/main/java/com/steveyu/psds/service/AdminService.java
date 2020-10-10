package com.steveyu.psds.service;

import com.steveyu.psds.entity.Admin;
import com.steveyu.psds.entity.Teacher;
import com.steveyu.psds.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository;

    public Page<Admin> getAllTeachers(Pageable pageable) {
        return adminRepository.findAll(pageable);
    }

    public void saveAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    public void deleteAdminById(Integer id) {
        adminRepository.deleteById(id);
    }

    public Optional<Admin> findAdminById(Integer adminId) {
        return adminRepository.findById(adminId);
    }
}
