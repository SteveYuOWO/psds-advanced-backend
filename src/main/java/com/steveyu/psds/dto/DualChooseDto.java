package com.steveyu.psds.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DualChooseDto {
    Integer id;
    Integer status;
    Integer studentId;
    Integer teacherId;
}
