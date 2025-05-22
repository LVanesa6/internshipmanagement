package com.uptc.bc.internshipmanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uptc.bc.internshipmanagement.dto.AddFeedbackDTO;
import com.uptc.bc.internshipmanagement.dto.CreateProgressDTO;
import com.uptc.bc.internshipmanagement.entity.Intern;
import com.uptc.bc.internshipmanagement.entity.Progress;
import com.uptc.bc.internshipmanagement.repository.InternRepository;
import com.uptc.bc.internshipmanagement.repository.ProgressRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProgressService {

    private final ProgressRepository progressRepository;
    private final InternRepository internRepository;

    // Crear un avance
    public Progress createProgress(CreateProgressDTO dto) {
        Intern intern = internRepository.findById(dto.getInternId())
                .orElseThrow(() -> new RuntimeException("Intern not found"));

        Progress progress = new Progress();
        progress.setDescription(dto.getDescription());
        progress.setRegistrationDate(dto.getRegistrationDate());
        progress.setIntern(intern);

        return progressRepository.save(progress);
    }

    // Actualizar un avance
    public Progress updateProgress(Integer id, CreateProgressDTO dto) {
        Progress progress = progressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Progress not found"));

        progress.setDescription(dto.getDescription());
        progress.setRegistrationDate(dto.getRegistrationDate());

        return progressRepository.save(progress);
    }

    // Eliminar un avance
    public void deleteProgress(Integer id) {
        if (!progressRepository.existsById(id)) {
            throw new RuntimeException("Progress not found");
        }
        progressRepository.deleteById(id);
    }

    // Agregar o actualizar feedback
    public Progress addFeedback(AddFeedbackDTO dto) {
        Progress progress = progressRepository.findById(dto.getProgressId())
                .orElseThrow(() -> new RuntimeException("Progress not found"));

        progress.setFeedback(dto.getFeedback());
        return progressRepository.save(progress);
    }

    // Obtener todos los avances de un practicante
    public List<Progress> getProgressByIntern(Integer internId) {
        Intern intern = internRepository.findById(internId)
                .orElseThrow(() -> new RuntimeException("Intern not found"));

        return progressRepository.findByIntern(intern);
    }
}
