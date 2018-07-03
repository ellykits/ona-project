package com.coder.nerdsoft.projectx.data.mapper;

import com.coder.nerdsoft.projectx.data.model.User;
import com.coder.nerdsoft.projectx.domain.entity.UserEntity;

import org.mapstruct.Mapper;

import java.util.List;
@Mapper
public interface UsersMapper {
    List<UserEntity> userDataToEntity(List<User> user);
    List<User> userEntityToData(List<UserEntity> entities);
}
