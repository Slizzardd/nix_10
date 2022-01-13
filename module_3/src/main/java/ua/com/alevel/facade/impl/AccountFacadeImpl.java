package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.AccountFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.service.AccountService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;
import ua.com.alevel.view.dto.request.AccountRequestDto;
import ua.com.alevel.view.dto.response.AccountResponseDto;
import ua.com.alevel.view.dto.response.PageData;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AccountFacadeImpl implements AccountFacade {

    private final AccountService accountService;

    public AccountFacadeImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void create(AccountRequestDto req) {
        Account account = new Account();
        account.setCardType(req.getCardType());
        accountService.create(account, req.getUserId());
    }

    @Override
    public void update(AccountRequestDto accountRequestDto, Long id) {
        Account account = accountService.findById(id);
        account.setCardType(accountRequestDto.getCardType());
        accountService.update(account);
    }

    @Override
    public void delete(Long id) {
        accountService.delete(id);
    }

    @Override
    public Map<Long, String> findUserByAccountId(Long accountId) {
        return accountService.findUserByAccountId(accountId);
    }

    @Override
    public AccountResponseDto findById(Long id) {
        return new AccountResponseDto(accountService.findById(id));
    }

    @Override
    public PageData<AccountResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        DataTableResponse<Account> tableResponse = accountService.findAll(dataTableRequest);

        List<AccountResponseDto> accountResponseDtos = tableResponse.getItems().stream().
                map(AccountResponseDto::new).
                peek(accountResponseDto -> accountResponseDto.setTransactionCount((Integer) tableResponse.
                        getOtherParamMap().get(accountResponseDto.getId()))).
                collect(Collectors.toList());

        PageData<AccountResponseDto> pageData = (PageData<AccountResponseDto>) WebResponseUtil.initPageData(tableResponse);
        pageData.setItems(accountResponseDtos);

        return pageData;
    }
}
