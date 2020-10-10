package com.steveyu.psds.service;

import com.steveyu.psds.dto.DualChooseDto;
import com.steveyu.psds.entity.DualChoose;
import com.steveyu.psds.entity.Student;
import com.steveyu.psds.entity.Teacher;
import com.steveyu.psds.repository.DualChooseRepository;
import com.steveyu.psds.repository.StudentRepository;
import com.steveyu.psds.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChooseService {
    @Autowired
    DualChooseRepository dualChooseRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    StudentRepository studentRepository;

    public void choose(Integer studentId, Integer teacherId, Integer status) {
        dualChooseRepository.save(new DualChoose(null,
                teacherRepository.findById(teacherId).orElse(null),
                studentRepository.findById(studentId).orElse(null),
                status));
    }

    public List<DualChoose> getAllByStudentId(Integer studentId) {
        return dualChooseRepository.findAllByStudentId(studentId);
    }

    public Page<DualChoose> getAllByStudentId(Pageable pageable, Integer studentId) {
        return dualChooseRepository.findAllByStudentId(pageable, studentId);
    }

    public Page<DualChoose> getAllByTeacherId(Pageable pageable, Integer teacherId) {
        return dualChooseRepository.findAllByTeacherId(pageable, teacherId);
    }

    public Optional<DualChoose> getByStudentIdAndTeacherId(Integer studentId, Integer teacherId) {
        return dualChooseRepository.findByStudentIdAndTeacherId(studentId, teacherId);
    }

    public boolean deleteChooseByStudentIdAndTeacherId(Integer studentId, Integer teacherId) {
        Optional<DualChoose> dualChooseOptional = getByStudentIdAndTeacherId(studentId, teacherId);
        if(dualChooseOptional.isEmpty()) return false;
        else {
            dualChooseRepository.delete(dualChooseOptional.get());
            return true;
        }
    }


    public Optional<DualChoose> getChooseById(Integer chooseId) {
        return dualChooseRepository.findById(chooseId);
    }

    public void saveChoose(DualChoose dualChoose) {
        dualChooseRepository.save(dualChoose);
    }

    public Page<DualChoose> getAllChooses(Pageable pageable) {
        return dualChooseRepository.findAll(pageable);
    }

    public Optional<DualChoose> findChooseById(Integer chooseId) {
        return dualChooseRepository.findById(chooseId);
    }
}
