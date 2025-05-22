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

    public Progress createProgress(CreateProgressDTO dto) {
        Intern intern = internRepository.findById(dto.getInternId())
                .orElseThrow(() -> new RuntimeException("Intern not found"));

        Progress progress = new Progress();
        progress.setDescription(dto.getDescription());
        progress.setRegistrationDate(dto.getRegistrationDate());  // <-- LocalDate ahora
        progress.setIntern(intern);

        return progressRepository.save(progress);
    }

    public Progress updateProgress(Integer id, CreateProgressDTO dto) {
        Progress progress = progressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Progress not found"));

        progress.setDescription(dto.getDescription());
        progress.setRegistrationDate(dto.getRegistrationDate());  // <-- LocalDate

        return progressRepository.save(progress);
    }

    public void deleteProgress(Integer id) {
        if (!progressRepository.existsById(id)) {
            throw new RuntimeException("Progress not found");
        }
        progressRepository.deleteById(id);
    }

    public Progress addFeedback(AddFeedbackDTO dto) {
        Progress progress = progressRepository.findById(dto.getProgressId())
                .orElseThrow(() -> new RuntimeException("Progress not found"));

        progress.setFeedback(dto.getFeedback());
        return progressRepository.save(progress);
    }

    public List<Progress> getProgressByIntern(Integer internId) {
        Intern intern = internRepository.findById(internId)
                .orElseThrow(() -> new RuntimeException("Intern not found"));

        return progressRepository.findByIntern(intern);
    }
}
