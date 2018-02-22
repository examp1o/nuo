package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.CrowdfundingWalletDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CrowdfundingWallet and its DTO CrowdfundingWalletDTO.
 */
@Mapper(componentModel = "spring", uses = {ProjectMapper.class})
public interface CrowdfundingWalletMapper extends EntityMapper<CrowdfundingWalletDTO, CrowdfundingWallet> {

    @Mapping(source = "project.id", target = "projectId")
    CrowdfundingWalletDTO toDto(CrowdfundingWallet crowdfundingWallet);

    @Mapping(source = "projectId", target = "project")
    CrowdfundingWallet toEntity(CrowdfundingWalletDTO crowdfundingWalletDTO);

    default CrowdfundingWallet fromId(Long id) {
        if (id == null) {
            return null;
        }
        CrowdfundingWallet crowdfundingWallet = new CrowdfundingWallet();
        crowdfundingWallet.setId(id);
        return crowdfundingWallet;
    }
}
