package ua.com.alevel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.persistence.dao.CategoryDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Category;
import ua.com.alevel.service.CategoryService;
import ua.com.alevel.util.WebResponseUtil;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    private final CategoryDao categoryDao;

    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public void create(Category category) {
        LOGGER_INFO.info("CREATING CATEGORY");
        categoryDao.create(category);
    }

    @Override
    public boolean existById(Long id) {
        return categoryDao.existById(id);
    }

    @Override
    public Category findById(Long id) {
        LOGGER_INFO.info("FIND CATEGORY BY ID: " + id);
        if(existById(id)){
            LOGGER_INFO.info("done");
            return categoryDao.findById(id);
        }else{
            LOGGER_INFO.warn("category not found");
            throw new EntityNotFoundException("category not found");
        }
    }

    @Override
    public DataTableResponse<Category> findAll(DataTableRequest request) {
        LOGGER_INFO.info("FIND ALL CATEGORIES");
        DataTableResponse<Category> dataTableResponse = categoryDao.findAll(request);
        long count = categoryDao.count();
        WebResponseUtil.initDataTableResponse(request, dataTableResponse, count);
        return dataTableResponse;
    }
}
