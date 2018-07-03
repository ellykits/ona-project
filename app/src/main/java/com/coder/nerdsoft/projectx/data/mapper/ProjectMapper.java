package com.coder.nerdsoft.projectx.data.mapper;

import com.coder.nerdsoft.projectx.data.model.Project;
import com.coder.nerdsoft.projectx.domain.entity.ProjectEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {FormsMapper.class,MetadataMapper.class,TeamsMapper.class,UsersMapper.class})
public interface ProjectMapper {

    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    @Mapping(target = "projectId", source = "projectid")
    @Mapping(target = "metadataEntity", source = "metadata")
    @Mapping(target = "formEntities", source = "forms")
    @Mapping(target = "teamEntities", source = "teams")
    @Mapping(target = "userEntities", source = "users")
    @Mapping(target = "lastSubmissionDate", dateFormat ="yyyy-MM-dd'T'HH:mm:ss.SSS")
    @Mapping(target = "dateCreated", dateFormat ="yyyy-MM-dd'T'HH:mm:ss.SSS")
    @Mapping(target = "dateModified", dateFormat ="yyyy-MM-dd'T'HH:mm:ss.SSS")
    ProjectEntity projectModelToEntity(Project project);

    @Mapping(target = "projectid", source = "projectId")
    @Mapping(target = "metadata", source = "metadataEntity")
    @Mapping(target = "forms", source = "formEntities")
    @Mapping(target = "teams", source = "teamEntities")
    @Mapping(target = "users", source = "userEntities")
    @Mapping(target = "lastSubmissionDate", dateFormat ="yyyy-MM-dd'T'HH:mm:ss.SSS")
    @Mapping(target = "dateCreated", dateFormat ="yyyy-MM-dd'T'HH:mm:ss.SSS")
    @Mapping(target = "dateModified", dateFormat ="yyyy-MM-dd'T'HH:mm:ss.SSS")
    Project projectEntityToModel(ProjectEntity entity);

}
