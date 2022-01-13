package ua.com.alevel.facade;

import ua.com.alevel.view.dto.request.CategoryRequestDto;
import ua.com.alevel.view.dto.response.CategoryResponseDto;

public interface CategoryFacade extends BaseFacade<CategoryRequestDto, CategoryResponseDto> {

    void create(CategoryRequestDto entity);
}
