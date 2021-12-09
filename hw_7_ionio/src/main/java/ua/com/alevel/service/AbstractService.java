package ua.com.alevel.service;

import ua.com.alevel.entity.BaseEntity;

import java.util.Set;

public interface AbstractService<ENTITY extends BaseEntity, ID extends Number> {
    void create(ENTITY entity);

    ENTITY find(ID id);

    Set<ENTITY> findAll();

    void update(ENTITY entity);

    void delete(ENTITY entity);
}