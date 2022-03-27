package com.etiya.renACar.core.utilities.mapper;

import java.util.List;

public interface MapperService <Entity, Request,Response>{

    List<Response> toResponse(List<Entity> entity);

    Entity toEntity(Request request);


}
