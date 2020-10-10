package com.steveyu.psds.repository;

import com.steveyu.psds.entity.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface MajorRepository extends JpaRepository<Major, Integer> {
    Optional<Major> findByName(String name);
}
