package ua.com.alevel.view.dto.request;

public class TransactionRequestDto extends RequestDto{

    private Double amount;

    private String categoryName;

    private String cardNumber;

    public TransactionRequestDto() {
    }

    public TransactionRequestDto(String cardNumber) {
        this.cardNumber = cardNumber;
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

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
