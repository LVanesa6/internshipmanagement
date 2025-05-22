package com.uptc.bc.internshipmanagement.dto;


import lombok.*;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InternDTO {

    private Integer id;
    private String name;
    private String academicProgram;
    private Date entryDate;
    private String practiceStatus;
    private Integer supervisorId;
}

