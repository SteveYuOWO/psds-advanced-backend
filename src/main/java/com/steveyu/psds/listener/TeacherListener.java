package com.steveyu.psds.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.steveyu.psds.entity.Teacher;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.List;

public class TeacherListener extends AnalysisEventListener<Teacher> {
    public static List<Teacher> teacherList = new ArrayList<>();

    @Override
    public void invoke(Teacher teacher, AnalysisContext context) {
        if (teacher != null) {
            teacher.setPassword(DigestUtils.md5DigestAsHex(teacher.getTeaNum().getBytes()));
            teacherList.add(teacher);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println("添加完成");
    }
}