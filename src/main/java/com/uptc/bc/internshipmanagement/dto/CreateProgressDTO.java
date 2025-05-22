package com.uptc.bc.internshipmanagement.dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProgressDTO {
    private String description;
    private LocalDate registrationDate;  // <-- Cambiado a LocalDate
    private Integer internId;
}
