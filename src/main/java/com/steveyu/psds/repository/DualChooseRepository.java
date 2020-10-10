package com.steveyu.psds.repository;

import com.steveyu.psds.dto.DualChooseDto;
import com.steveyu.psds.entity.DualChoose;
import com.steveyu.psds.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DualChooseRepository extends JpaRepository<DualChoose, Integer> {

    Page<DualChoose> findAllByStudentId(Pageable pageable, Integer studentId);

    List<DualChoose> findAllByStudentId(Integer studentId);

    Optional<DualChoose> findByStudentIdAndTeacherId(Integer studentId, Integer teacherId);

    Page<DualChoose> findAllByTeacherId(Pageable pageable, Integer teacherId);
}
