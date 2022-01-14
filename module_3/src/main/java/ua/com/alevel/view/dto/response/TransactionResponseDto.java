package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Transaction;

import java.util.HashMap;
import java.util.Map;

public class TransactionResponseDto extends ResponseDto{

    private Double amount;
    private String categoryName;
    private boolean income;
    private Map<Long, String> mapAccount;

    public TransactionResponseDto() {
    }

    public TransactionResponseDto(Transaction transaction) {
        setId(transaction.getId());
        setCreated(transaction.getCreated());
        setUpdated(transaction.getUpdated());
        setVisible(transaction.getVisible());
        this.amount = transaction.getAmount();
        this.categoryName = transaction.getCategory().getName();
        this.income = transaction.getCategory().getIncome();
        this.mapAccount = new HashMap<>();
        mapAccount.put(transaction.getAccount().getId(), transaction.getAccount().getCardNumber());
    }

    public Map<Long, String> getMapAccount() {
        return mapAccount;
    }

    public void setMapAccount(Map<Long, String> mapAccount) {
        this.mapAccount = mapAccount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isIncome() {
        return income;
    }

    public void setIncome(boolean income) {
        this.income = income;
    }
}
