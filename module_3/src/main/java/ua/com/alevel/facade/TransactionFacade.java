package ua.com.alevel.facade;

import ua.com.alevel.view.dto.request.TransactionRequestDto;
import ua.com.alevel.view.dto.response.TransactionResponseDto;

public interface TransactionFacade extends BaseFacade<TransactionRequestDto, TransactionResponseDto> {

    void create(TransactionRequestDto entity) throws Exception;

}
