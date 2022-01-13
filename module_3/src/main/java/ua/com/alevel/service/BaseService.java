package ua.com.alevel.service;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.BaseEntity;

public interface BaseService<ENTITY extends BaseEntity> {

    boolean existById(Long id);

    ENTITY findById(Long id);

    DataTableResponse<ENTITY> findAll(DataTableRequest request);

}
