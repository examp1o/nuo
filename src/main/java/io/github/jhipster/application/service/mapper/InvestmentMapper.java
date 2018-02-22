package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.InvestmentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Investment and its DTO InvestmentDTO.
 */
@Mapper(componentModel = "spring", uses = {ProjectMapper.class})
public interface InvestmentMapper extends EntityMapper<InvestmentDTO, Investment> {

    @Mapping(source = "project.id", target = "projectId")
    InvestmentDTO toDto(Investment investment);

    @Mapping(source = "projectId", target = "project")
    Investment toEntity(InvestmentDTO investmentDTO);

    default Investment fromId(Long id) {
        if (id == null) {
            return null;
        }
        Investment investment = new Investment();
        investment.setId(id);
        return investment;
    }
}
