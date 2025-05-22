package com.uptc.bc.internshipmanagement.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

import com.uptc.bc.internshipmanagement.entity.Progress;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProgressDTO {
    private Integer id;
    private String description;
    private Date registrationDate;
    private String feedback;
    private Integer internId;

    public ProgressDTO(Progress progress) {
        this.id = progress.getId();
        this.description = progress.getDescription();
        this.registrationDate = progress.getRegistrationDate();
        this.feedback = progress.getFeedback();
        this.internId = progress.getIntern() != null ? progress.getIntern().getId() : null;
    }

}

