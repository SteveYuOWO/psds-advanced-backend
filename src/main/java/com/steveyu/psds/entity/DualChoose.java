package com.steveyu.psds.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * 双选类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class DualChoose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @OneToOne(targetEntity = Teacher.class)
    Teacher teacher;

    @OneToOne(targetEntity = Student.class)
    Student student;

    /**
     * status 状态， 0为已经选择该老师，1为老师已经选择该学生，2为管理员通过确认
     */
    Integer status;
}
