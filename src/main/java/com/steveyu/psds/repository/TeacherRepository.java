package com.steveyu.psds.repository;

import com.steveyu.psds.entity.Student;
import com.steveyu.psds.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Optional<Teacher> findByTeaNum(String teaNum);
}
