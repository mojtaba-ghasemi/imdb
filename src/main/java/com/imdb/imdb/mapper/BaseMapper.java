package com.imdb.imdb.mapper;

import java.util.List;

public interface BaseMapper<E,D> {
    E mapToEntity(D dto);
    D mapToDto(E entity);
    List<E> toEntityList(List<D> dtoList);
    List<D> toDtoList(List<E> entityList);
}
