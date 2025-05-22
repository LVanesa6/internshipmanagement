package com.uptc.bc.internshipmanagement.mapper;

import com.uptc.bc.internshipmanagement.dto.CreateProgressDTO;
import com.uptc.bc.internshipmanagement.entity.Intern;
import com.uptc.bc.internshipmanagement.entity.Progress;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProgressMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "feedback", ignore = true)
    @Mapping(source = "intern", target = "intern")
    Progress toEntity(CreateProgressDTO dto, Intern intern);
}
