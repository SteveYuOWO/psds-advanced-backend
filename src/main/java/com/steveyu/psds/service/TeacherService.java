package com.steveyu.psds.service;

import com.steveyu.psds.entity.Teacher;
import com.steveyu.psds.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    @Autowired
    TeacherRepository teacherRepository;

    public Page<Teacher> getAllTeachersByMajor(Pageable pageable, String major) {
        Teacher teacher = new Teacher();
        teacher.setMajor(major);
        Example<Teacher> findBymajor = Example.of(teacher);
        return teacherRepository.findAll(findBymajor, pageable);
    }

    public Page<Teacher> getAllTeachers(Pageable pageable) {
        return teacherRepository.findAll(pageable);
    }

    public void saveTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    public void deleteTeacherById(Integer id) {
        teacherRepository.deleteById(id);
    }

    public List<Teacher> saveAllTeacher(List<Teacher> teacherList) {
        return teacherRepository.saveAll(teacherList);
    }


    public Optional<Teacher> findTeacherById(Integer teacherId) {
        return teacherRepository.findById(teacherId);
    }
}
