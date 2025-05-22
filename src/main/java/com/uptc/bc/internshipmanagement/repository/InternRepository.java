package com.uptc.bc.internshipmanagement.repository;

import com.uptc.bc.internshipmanagement.entity.Intern;
import com.uptc.bc.internshipmanagement.entity.Intern.PracticeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternRepository extends JpaRepository<Intern, Integer> {

    List<Intern> findByPracticeStatus(PracticeStatus status);
    List<Intern> findBySupervisorId(Integer supervisorId);

}
