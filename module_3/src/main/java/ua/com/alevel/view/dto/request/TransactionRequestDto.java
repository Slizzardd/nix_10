package ua.com.alevel.view.dto.request;

public class TransactionRequestDto extends RequestDto{

    private Double amount;

    private String categoryName;

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
}
