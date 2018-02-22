package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.BulletinDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Bulletin and its DTO BulletinDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BulletinMapper extends EntityMapper<BulletinDTO, Bulletin> {



    default Bulletin fromId(Long id) {
        if (id == null) {
            return null;
        }
        Bulletin bulletin = new Bulletin();
        bulletin.setId(id);
        return bulletin;
    }
}
