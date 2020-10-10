package com.steveyu.psds.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.steveyu.psds.entity.Student;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.List;

public class StudentListener extends AnalysisEventListener<Student> {
    public static List<Student> studentList = new ArrayList<>();

    @Override
    public void invoke(Student student, AnalysisContext context) {
        if (student != null) {
            student.setPassword(DigestUtils.md5DigestAsHex(student.getStuNum().getBytes()));
            studentList.add(student);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println("添加完成");
    }
}