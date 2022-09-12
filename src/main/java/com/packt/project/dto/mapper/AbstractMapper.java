package com.packt.project.dto.mapper;

import java.util.List;

public abstract class AbstractMapper <Entity, Dto>{
    public abstract Dto convertToDto(Entity entity);
    public abstract List<Dto> convertToDto(List<Entity> entity);
    public abstract Entity convertToEntity(Dto dto);
}
