package com.steveyu.psds.service;

import com.steveyu.psds.entity.Admin;
import com.steveyu.psds.entity.Major;
import com.steveyu.psds.entity.Root;
import com.steveyu.psds.repository.MajorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MajorService {
    @Autowired
    MajorRepository majorRepository;

    public Page<Major> getAllMajors(Pageable pageable) {
        return majorRepository.findAll(pageable);
    }

    public void save(Major major) {
        majorRepository.save(major);
    }

    public Major findMajorByName(String name) {
        return majorRepository.findByName(name).orElse(null);
    }

    public void deleteMajorById(Integer id) {
        majorRepository.deleteById(id);
    }
}
