package com.steveyu.psds.api;

import com.alibaba.excel.EasyExcel;
import com.steveyu.psds.dto.User;
import com.steveyu.psds.entity.Major;
import com.steveyu.psds.entity.Student;
import com.steveyu.psds.entity.Teacher;
import com.steveyu.psds.listener.StudentListener;
import com.steveyu.psds.service.LoginService;
import com.steveyu.psds.service.MajorService;
import com.steveyu.psds.service.StudentService;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/student")
public class StudentApi {
    @Autowired
    StudentService studentService;

    @Autowired
    MajorService majorService;


    @GetMapping(path = "getByMajor/{page}/{size}/{major}")
    public Page<Student> getAllStudentsByMajor(@PathVariable("page") int page,
                                               @PathVariable("size") int size,
                                               @PathVariable("major") String major) {
        return studentService.getAllStudentsByMajor(PageRequest.of(page, size), major);
    }

    @GetMapping(path = "{page}/{size}")
    public Page<Student> getAllStudents(@PathVariable("page") int page,
                                        @PathVariable("size") int size) {
        return studentService.getAllStudents(PageRequest.of(page, size));
    }

    @PostMapping("update")
    public String updateStudent(@RequestBody Student student) {
        Optional<Student> studentOptional = studentService.findStudentById(student.getId());
        if(studentOptional.isEmpty()) {
            return "修改失败, 请重新登录";
        } else {
            studentService.saveStudent(student);
            return "修改成功";
        }
    }
    @PostMapping
    public String saveStudent(@RequestBody Student student) {
        student.setPassword(DigestUtils.md5DigestAsHex(student.getPassword().getBytes()));
        studentService.saveStudent(student);
        if (majorService.findMajorByName(student.getMajor()) == null)
            majorService.save(new Major(null, student.getMajor(), 0, ""));
        return "添加成功";
    }

    @DeleteMapping(path = "{id}")
    public String deleteStudentById(@PathVariable("id") Integer id) {
        studentService.deleteStudentById(id);
        return "删除成功";
    }

    @GetMapping(path = "{id}")
    public Optional<Student> findStudentById(@PathVariable("id") Integer id) {
        return studentService.findStudentById(id);
    }

    @RequestMapping("upload")
    public String uploadStudentFile(@RequestParam("file") MultipartFile file) throws Exception {
        // 上传文件存储目录
        String UPLOAD_DIRECTORY = "upload";
        // 上传配置
        int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
        int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
        int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 设置最大文件上传值
        upload.setFileSizeMax(MAX_FILE_SIZE);
        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(MAX_REQUEST_SIZE);
        // 中文处理
        upload.setHeaderEncoding("UTF-8");
        // 构造临时路径来存储上传的文件
        // 这个路径相对当前应用的目录
        String uploadPath = UPLOAD_DIRECTORY;
        // 如果目录不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        try {
            String fileName = new File(file.getName()).getName();
            String filePath = uploadPath + File.separator + fileName;
            File storeFile = new File(filePath);
            // 保存文件到硬盘
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(storeFile));
            bos.write(file.getBytes());
            EasyExcel.read(storeFile, Student.class, new StudentListener()).sheet().doRead();

            List<Student> list = studentService.saveAllStudent(StudentListener.studentList);
            int size = list.size();
            StudentListener.studentList.clear();

            File delFile = new File(filePath);
            delFile.delete();

            // 专业增加
            list.forEach(student -> {
                if (majorService.findMajorByName(student.getMajor()) == null)
                    majorService.save(new Major(null, student.getMajor(), 0, ""));
            });
            return "批量导入" + size + "条记录成功";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Excel格式错误";
        }
    }
}
