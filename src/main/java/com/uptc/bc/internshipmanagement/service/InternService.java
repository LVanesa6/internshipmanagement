package com.uptc.bc.internshipmanagement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.uptc.bc.internshipmanagement.dto.InternDTO;
import com.uptc.bc.internshipmanagement.entity.Intern;
import com.uptc.bc.internshipmanagement.entity.User;
import com.uptc.bc.internshipmanagement.repository.InternRepository;
import com.uptc.bc.internshipmanagement.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InternService {

    private final InternRepository internRepository;
    private final UserRepository userRepository;
    private final KeycloakService keycloakService;

    public InternDTO createIntern(InternDTO dto) {
        String baseUsername = dto.getName().split(" ")[0].toLowerCase();
        String username = generateUniqueUsername(baseUsername);

        // Crear usuario en Keycloak
        keycloakService.createUser(username, "123", List.of("INTERN"));

        // Guardar usuario en base de datos local
        User localUser = new User();
        localUser.setUsername(username);
        localUser.setRole(User.Role.INTERN);
        localUser = userRepository.save(localUser);

        // Crear Intern y asociar
        Intern intern = new Intern();
        intern.setName(dto.getName());
        intern.setAcademicProgram(dto.getAcademicProgram());
        intern.setEntryDate(dto.getEntryDate());
        intern.setPracticeStatus(Intern.PracticeStatus.valueOf(dto.getPracticeStatus()));
        intern.setSupervisor(userRepository.findById(dto.getSupervisorId())
                .orElseThrow(() -> new RuntimeException("Supervisor not found")));
        internRepository.save(intern);

        // Asociar Intern al usuario
        localUser.setIntern(intern);
        userRepository.save(localUser);

        return toDTO(intern);
    }

    public void deleteIntern(Integer id) {
        Intern intern = internRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Intern not found"));

        // Buscar el usuario asociado al Intern
        User user = userRepository.findByIntern(intern)
                .orElseThrow(() -> new RuntimeException("Associated user not found"));

        String username = user.getUsername();

        // Eliminar usuario de Keycloak
        keycloakService.deleteUserByUsername(username);

        // Eliminar usuario de base de datos local
        userRepository.delete(user);

        // Eliminar Intern
        internRepository.delete(intern);
    }

    private String generateUniqueUsername(String baseUsername) {
        int counter = 1;
        String candidate = baseUsername + counter;

        while (userRepository.existsByUsername(candidate)) {
            counter++;
            candidate = baseUsername + counter;
        }

        return candidate;
    }

    private InternDTO toDTO(Intern intern) {
        return new InternDTO(
                intern.getId(),
                intern.getName(),
                intern.getAcademicProgram(),
                intern.getEntryDate(),
                intern.getPracticeStatus().name(),
                intern.getSupervisor() != null ? intern.getSupervisor().getId() : null
        );
    }

public List<InternDTO> getAllInterns() {
    return internRepository.findAll()
            .stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
}
public InternDTO getInternById(Integer id) {
    Intern intern = internRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Intern not found"));
    return toDTO(intern);
}

public List<InternDTO> getInternsByStatus(String status) {
    return internRepository.findByPracticeStatus(Intern.PracticeStatus.valueOf(status))
            .stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
}
}
