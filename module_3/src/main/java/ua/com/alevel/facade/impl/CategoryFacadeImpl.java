package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.CategoryFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Category;
import ua.com.alevel.service.CategoryService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;
import ua.com.alevel.view.dto.request.CategoryRequestDto;
import ua.com.alevel.view.dto.response.CategoryResponseDto;
import ua.com.alevel.view.dto.response.PageData;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryFacadeImpl implements CategoryFacade {

    private final CategoryService categoryService;

    public CategoryFacadeImpl(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void create(CategoryRequestDto categoryRequestDto) {
        Category category = new Category();
        category.setName(categoryRequestDto.getName());
        category.setIncome(categoryRequestDto.isIncome());
        categoryService.create(category);
    }

    @Override
    public CategoryResponseDto findById(Long id) {
        return new CategoryResponseDto(categoryService.findById(id));
    }

    @Override
    public PageData<CategoryResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        DataTableResponse<Category> tableResponse = categoryService.findAll(dataTableRequest);

        List<CategoryResponseDto> categoryResponseDtos = tableResponse.getItems().stream().
                map(CategoryResponseDto::new).
                peek(accountResponseDto -> accountResponseDto.setTransactionCount((Integer) tableResponse.
                        getOtherParamMap().get(accountResponseDto.getId()))).
                collect(Collectors.toList());

        PageData<CategoryResponseDto> pageData = (PageData<CategoryResponseDto>) WebResponseUtil.initPageData(tableResponse);
        pageData.setItems(categoryResponseDtos);

        return pageData;
    }
}
