package ua.com.alevel.facade;

import ua.com.alevel.view.dto.request.AccountRequestDto;
import ua.com.alevel.view.dto.response.AccountResponseDto;

import java.util.Map;

public interface AccountFacade extends BaseFacade<AccountRequestDto, AccountResponseDto> {

    void create(AccountRequestDto entity);

    void update(AccountRequestDto entity, Long id);

    void delete(Long id);

    Map<Long, String> findUserByAccountId(Long accountId);
}