package com.steveyu.psds.api;

import com.steveyu.psds.entity.DualChoose;
import com.steveyu.psds.service.ChooseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/select")
public class SelectApi {
    @Autowired
    ChooseService chooseService;

    private String chooseByIdAndStatus(Integer chooseId, Integer status) {
        Optional<DualChoose> dualChooseOptional = chooseService.getChooseById(chooseId);
        if(dualChooseOptional.isEmpty()) {
            return "选择失败";
        } else {
            DualChoose dualChoose = dualChooseOptional.get();
            dualChoose.setStatus(status);
            chooseService.saveChoose(dualChoose);
            return "选择成功";
        }
    }

    @GetMapping("{page}/{size}")
    public Page<DualChoose> getAllDualChooses(@PathVariable("page") Integer page,
                                              @PathVariable("size") Integer size) {
        return chooseService.getAllChooses(PageRequest.of(page, size));
    }

    /**
     * 最终选择，状态置为2
     * @param chooseId
     * @return
     */
    @PostMapping("unFinalChoose/{chooseId}")
    public String unfinalChoose(@PathVariable("chooseId") Integer chooseId) {
        return this.chooseByIdAndStatus(chooseId, 1);
    }
    /**
     * 最终选择，状态置为2
     * @param chooseId
     * @return
     */
    @PostMapping("finalChoose/{chooseId}")
    public String finalChoose(@PathVariable("chooseId") Integer chooseId) {
        DualChoose dualChoose = chooseService.findChooseById(chooseId).get();

        Integer studentId = dualChoose.getStudent().getId();
        List<DualChoose> lists = chooseService.getAllByStudentId(studentId);
        for (DualChoose tmp : lists) {
            if(tmp.getStatus() == 2) return "该学生已有导师，选择失败";
        }
        return this.chooseByIdAndStatus(chooseId, 2);
    }

    /**
     * 老师选学生，状态设置为1
     * @param chooseId
     * @return
     */
    @PostMapping("teacherChooseStudent/{chooseId}")
    public String chooseStudent(@PathVariable("chooseId") Integer chooseId) {
        return this.chooseByIdAndStatus(chooseId, 1);
    }

    /**
     * 老师取消选择，状态置为0
     * @param chooseId
     * @return
     */
    @PostMapping("teacherUnChooseStudent/{chooseId}")
    public String teacherUnChooseStudent(@PathVariable("chooseId") Integer chooseId) {
        return this.chooseByIdAndStatus(chooseId, 0);
    }

    @PostMapping("studentChooseTeacher/{studentId}/{teacherId}")
    public String chooseTeacher(@PathVariable("studentId") Integer studentId,
                                @PathVariable("teacherId") Integer teacherId) {
        List<DualChoose> lists = chooseService.getAllByStudentId(studentId);
        if(lists.size() == 3) return "选择失败, 不能选择超过3个老师";
        // 如果存在，返回失败
        if(chooseService.getByStudentIdAndTeacherId(studentId, teacherId).isEmpty()) {
            chooseService.choose(studentId, teacherId, 0);
            return "选择成功";
        }
        return "选择失败, 您已选择该老师";
    }

    @GetMapping("student/hasChoose/{page}/{size}/{studentId}")
    public Page<DualChoose> findChooseByStudentId(@PathVariable("page") int page,
                                      @PathVariable("size") int size,
                                      @PathVariable("studentId") Integer studentId) {
        return chooseService.getAllByStudentId(PageRequest.of(page, size), studentId);
    }

    @GetMapping("teacher/hasChoose/{page}/{size}/{teacherId}")
    public Page<DualChoose> findChooseByTeacherId(@PathVariable("page") Integer page,
                                                  @PathVariable("size") Integer size,
                                                  @PathVariable("teacherId") Integer teacherId) {
        return chooseService.getAllByTeacherId(PageRequest.of(page, size), teacherId);
    }

    @DeleteMapping("student/{studentId}/{teacherId}")
    public String deleteChooseByStudentIdAndTeacherId(@PathVariable("studentId")Integer studentId,
                         @PathVariable("teacherId")Integer teacherId) {
        if(chooseService.deleteChooseByStudentIdAndTeacherId(studentId, teacherId)) return "删除成功";
        else return "删除失败";
    }
}
