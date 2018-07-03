package com.coder.nerdsoft.projectx.data.mapper;

import com.coder.nerdsoft.projectx.data.model.Team;
import com.coder.nerdsoft.projectx.domain.entity.TeamEntity;

import org.mapstruct.Mapper;

import java.util.List;
@Mapper
public interface TeamsMapper {
    List<TeamEntity> teamDataToEntity(List<Team> team);
    List<Team> teamEntityToData(List<TeamEntity> entities);
}
