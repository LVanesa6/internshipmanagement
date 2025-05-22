package com.uptc.bc.internshipmanagement.repository;

import com.uptc.bc.internshipmanagement.entity.Intern;
import com.uptc.bc.internshipmanagement.entity.User;
import com.uptc.bc.internshipmanagement.entity.User.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByRole(Role role);

    User findByUsername(String username);

    Optional<User> findByIntern(Intern intern);

    boolean existsByUsername(String username); // ✅ <--- Esto es lo que te falta
}
