package com.steveyu.psds.repository;

import com.steveyu.psds.entity.Admin;
import com.steveyu.psds.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Optional<Admin> findByName(String name);
}
