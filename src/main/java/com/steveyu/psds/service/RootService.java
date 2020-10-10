package com.steveyu.psds.service;

import com.steveyu.psds.entity.Root;
import com.steveyu.psds.repository.RootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RootService {
    @Autowired
    RootRepository rootRepository;

    public Optional<Root> findRootById(Integer rootId) {
        return rootRepository.findById(rootId);
    }

    public void saveRoot(Root root) {
        rootRepository.save(root);
    }
}
