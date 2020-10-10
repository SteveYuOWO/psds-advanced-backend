package com.steveyu.psds.api;

import com.steveyu.psds.entity.Admin;
import com.steveyu.psds.entity.Major;
import com.steveyu.psds.entity.Root;
import com.steveyu.psds.service.AdminService;
import com.steveyu.psds.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/major")
public class MajorApi {
    @Autowired
    MajorService majorService;

    @GetMapping(path = "{page}/{size}")
    public Page<Major> getAllMajors(@PathVariable("page") int page, @PathVariable("size") int size) {
        return majorService.getAllMajors(PageRequest.of(page, size));
    }

    @DeleteMapping(path = "{id}")
    public String deleteMajorById(@PathVariable("id") Integer id) {
        majorService.deleteMajorById(id);
        return "删除成功";
    }
}
