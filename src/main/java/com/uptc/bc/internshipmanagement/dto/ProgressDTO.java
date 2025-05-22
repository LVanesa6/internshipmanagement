package com.uptc.bc.internshipmanagement.dto;

import lombok.*;
import java.time.LocalDate;

import com.uptc.bc.internshipmanagement.entity.Progress;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProgressDTO {
    private Integer id;
    private String description;
    private LocalDate registrationDate;  // <-- Cambiado a LocalDate
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
