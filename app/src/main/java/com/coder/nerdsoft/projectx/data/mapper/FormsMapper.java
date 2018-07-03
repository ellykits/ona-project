package com.coder.nerdsoft.projectx.data.mapper;

import com.coder.nerdsoft.projectx.data.model.Form;
import com.coder.nerdsoft.projectx.domain.entity.FormEntity;

import org.mapstruct.Mapper;

import java.util.List;
@Mapper
public interface FormsMapper {
    List<FormEntity> formDataToEntity(List<Form> forms);
    List<Form> formEntityToData(List<FormEntity> formEntities);
}
