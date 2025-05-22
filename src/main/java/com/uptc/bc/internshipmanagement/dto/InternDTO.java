package com.uptc.bc.internshipmanagement.dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InternDTO {

    private Integer id;
    private String name;
    private String academicProgram;
    private LocalDate entryDate; 
    private String practiceStatus;
    private Integer supervisorId;
}
