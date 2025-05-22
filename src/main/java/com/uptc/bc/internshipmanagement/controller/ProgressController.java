package com.uptc.bc.internshipmanagement.controller;

import com.uptc.bc.internshipmanagement.dto.AddFeedbackDTO;
import com.uptc.bc.internshipmanagement.dto.CreateProgressDTO;
import com.uptc.bc.internshipmanagement.dto.ProgressDTO;
import com.uptc.bc.internshipmanagement.entity.Progress;
import com.uptc.bc.internshipmanagement.service.ProgressService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/progress")
@RequiredArgsConstructor
public class ProgressController {

    private final ProgressService progressService;

    @PostMapping
    public ResponseEntity<Progress> createProgress(@RequestBody CreateProgressDTO dto) {
        Progress created = progressService.createProgress(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Progress> updateProgress(@PathVariable Integer id, @RequestBody CreateProgressDTO dto) {
        Progress updated = progressService.updateProgress(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgress(@PathVariable Integer id) {
        progressService.deleteProgress(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/feedback")
    public ResponseEntity<Progress> addFeedback(@RequestBody AddFeedbackDTO dto) {
        Progress updated = progressService.addFeedback(dto);
        return ResponseEntity.ok(updated);
    }

   @GetMapping("/intern/{internId}")
    public ResponseEntity<List<ProgressDTO>> getProgressByIntern(@PathVariable Integer internId) {
        List<Progress> avances = progressService.getProgressByIntern(internId);
        List<ProgressDTO> dtos = avances.stream()
                                       .map(progress -> new ProgressDTO(progress))
                                       .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
    
}
