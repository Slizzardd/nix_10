package ua.com.alevel.dao;

import ua.com.alevel.persistence.entity.BaseEntity;

import java.util.Set;

public interface AbstractDao<ENTITY extends BaseEntity, ID extends Number> {

    void create(ENTITY entity);

    ENTITY find(ID id);

    Set<ENTITY> find();

    void update(ENTITY entity);

    void delete(ENTITY entity);
}
