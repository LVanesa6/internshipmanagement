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
        List<InternDTO> interns = internService.getInternsByStatus(status);
        return ResponseEntity.ok(interns);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InternDTO> updateIntern(@PathVariable Integer id, @RequestBody InternDTO dto) {
        InternDTO updated = internService.updateIntern(id, dto);
        return ResponseEntity.ok(updated);
    }

    @GetMapping(params = "supervisorId")
    public ResponseEntity<List<InternDTO>> getInternsBySupervisor(@RequestParam Integer supervisorId) {
        return ResponseEntity.ok(internService.getInternsBySupervisor(supervisorId));
    }

}
