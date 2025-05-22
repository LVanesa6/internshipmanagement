package com.uptc.bc.internshipmanagement.mapper;


import com.uptc.bc.internshipmanagement.dto.InternDTO;
import com.uptc.bc.internshipmanagement.entity.Intern;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface InternMapper {

    @Mapping(source = "supervisor.id", target = "supervisorId")
    @Mapping(source = "practiceStatus", target = "practiceStatus")
    InternDTO toDTO(Intern intern);
}
