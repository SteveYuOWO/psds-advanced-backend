package com.steveyu.psds.repository;

import com.steveyu.psds.entity.Root;
import com.steveyu.psds.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RootRepository extends JpaRepository<Root, Integer> {
    Optional<Root> findByName(String name);
}
