package com.uptc.bc.internshipmanagement.service;

import com.uptc.bc.internshipmanagement.dto.InternDTO;
import com.uptc.bc.internshipmanagement.dto.UserDTO;
import com.uptc.bc.internshipmanagement.entity.User;
import com.uptc.bc.internshipmanagement.mapper.UserMapper;
import com.uptc.bc.internshipmanagement.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDTO getUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        UserDTO dto = userMapper.toDTO(user);
        if (user.getIntern() != null) {
            InternDTO internDTO = new InternDTO();
            internDTO.setId(user.getIntern().getId());
            dto.setIntern(internDTO);
        }
        return dto;
    }

    public UserDTO saveUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> {
                    UserDTO dto = userMapper.toDTO(user);
                    if (user.getIntern() != null) {
                        InternDTO internDTO = new InternDTO();
                        internDTO.setId(user.getIntern().getId());
                        dto.setIntern(internDTO);
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public List<UserDTO> getSupervisors() {
        return userRepository.findByRole(User.Role.SUPERVISOR)
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }
}
