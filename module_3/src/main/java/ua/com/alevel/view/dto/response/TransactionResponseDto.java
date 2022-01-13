package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Transaction;

public class TransactionResponseDto extends ResponseDto{

    private Double amount;
    private String categoryName;
    private boolean income;

    public TransactionResponseDto(Transaction transaction) {
        setId(transaction.getId());
        setCreated(transaction.getCreated());
        setUpdated(transaction.getUpdated());
        setVisible(transaction.getVisible());
        this.amount = transaction.getAmount();
        this.categoryName = transaction.getCategory().getName();
        this.income = transaction.getCategory().getIncome();
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
