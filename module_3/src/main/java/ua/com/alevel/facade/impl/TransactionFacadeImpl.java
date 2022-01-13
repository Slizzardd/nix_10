package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.TransactionFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Category;
import ua.com.alevel.persistence.entity.Transaction;
import ua.com.alevel.service.TransactionService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;
import ua.com.alevel.view.dto.request.TransactionRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.TransactionResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionFacadeImpl implements TransactionFacade {

    private final TransactionService transactionService;

    public TransactionFacadeImpl(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public void create(TransactionRequestDto req, String temporalField) {
        Transaction transaction = new Transaction();
        Category category = new Category();
        transaction.setAmount(req.getAmount());
        if(req.getAmount() > 0){
            category.setIncome(true);
        }else{
            category.setIncome(false);

        }
        transaction.setCategory(category);
    }

    @Override
    public TransactionResponseDto findById(Long id) {
        return new TransactionResponseDto(transactionService.findById(id));
    }

    @Override
    public PageData<TransactionResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        DataTableResponse<Transaction> tableResponse = transactionService.findAll(dataTableRequest);

        List<TransactionResponseDto> transactions = tableResponse.getItems().stream().
                map(TransactionResponseDto::new).
                collect(Collectors.toList());

        PageData<TransactionResponseDto> pageData = (PageData<TransactionResponseDto>) WebResponseUtil.initPageData(tableResponse);
        pageData.setItems(transactions);

        return pageData;
    }
}
