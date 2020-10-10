package com.steveyu.psds.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 日志类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class SystemLog {
    @Id
    private Integer id;
    private String ip, name, type, time, comment;
}
