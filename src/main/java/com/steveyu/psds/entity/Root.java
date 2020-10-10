package com.steveyu.psds.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 超管类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Root {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rootId;

    private String name, sex, password;
}
