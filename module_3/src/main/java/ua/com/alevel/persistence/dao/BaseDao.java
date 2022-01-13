package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.BaseEntity;

public interface BaseDao<ENTITY extends BaseEntity> {


    boolean existById(Long id);

    ENTITY findById(Long id);

    DataTableResponse<ENTITY> findAll(DataTableRequest request);

    long count();
}
