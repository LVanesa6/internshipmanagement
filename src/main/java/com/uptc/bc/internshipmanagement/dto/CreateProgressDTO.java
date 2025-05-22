package com.uptc.bc.internshipmanagement.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProgressDTO {

    private String description;
    private Date registrationDate;
    private Integer internId;
}
