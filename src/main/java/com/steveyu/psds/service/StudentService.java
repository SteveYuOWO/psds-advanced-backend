package com.steveyu.psds.service;

import com.steveyu.psds.entity.Student;
import com.steveyu.psds.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public Page<Student> getAllStudentsByMajor(Pageable pageable, String major) {
        Student student = new Student();
        student.setMajor(major);
        Example<Student> queryByMajor = Example.of(student);
        return studentRepository.findAll(queryByMajor, pageable);
    }

    public Page<Student> getAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudentById(Integer studentId) {
        studentRepository.deleteById(studentId);
    }

    public Optional<Student> findStudentById(Integer id) {
        return studentRepository.findById(id);
    }

    public List<Student> saveAllStudent(Iterable<Student> students) {
        return studentRepository.saveAll(students);
    }


}
