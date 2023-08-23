package com.companygroup.company.mapper;

import java.util.List;

public interface BaseMapper<E,M> {
    M toModel(E entity);

    E toEntity(M model);

    List<M> toModel(List<E> modelList);

    List<E> toEntity(List<M> entityList);
}