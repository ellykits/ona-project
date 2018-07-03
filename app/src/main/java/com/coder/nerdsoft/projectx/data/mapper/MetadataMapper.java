package com.coder.nerdsoft.projectx.data.mapper;

import com.coder.nerdsoft.projectx.data.model.Metadata;
import com.coder.nerdsoft.projectx.domain.entity.MetadataEntity;

import org.mapstruct.Mapper;

@Mapper
public interface MetadataMapper {
    MetadataEntity metadataDataToEntity(Metadata metadata);
    Metadata metadataEntityToData(MetadataEntity entity);
}
