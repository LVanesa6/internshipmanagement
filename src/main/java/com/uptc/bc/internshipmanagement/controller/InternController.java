package com.uptc.bc.internshipmanagement.controller;


import com.uptc.bc.internshipmanagement.dto.InternDTO;
import com.uptc.bc.internshipmanagement.service.InternService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interns")
@RequiredArgsConstructor
public class InternController {

    private final InternService internService;

    @PostMapping
    public ResponseEntity<InternDTO> createIntern(@RequestBody InternDTO dto) {
        InternDTO created = internService.createIntern(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<InternDTO>> getAllInterns() {
        return ResponseEntity.ok(internService.getAllInterns());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InternDTO> getInternById(@PathVariable Integer id) {
        InternDTO intern = internService.getInternById(id);
        return ResponseEntity.ok(intern);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIntern(@PathVariable Integer id) {
        internService.deleteIntern(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<InternDTO>> getInternsByStatus(@PathVariable String status) {
        return ResponseEntity.ok(internService.getInternsByStatus(status));
    }
}
