package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.type.CardType;

import java.util.Map;

public class AccountResponseDto extends ResponseDto {

    private String cardNumber;
    private Double balance;
    private CardType cardType;
    private Integer transactionCount;
    private Map<Long, String> mapUser;

    public AccountResponseDto() {
    }

    public AccountResponseDto(Account account) {
        setId(account.getId());
        setCreated(account.getCreated());
        setUpdated(account.getUpdated());
        setVisible(account.getVisible());
        this.balance = account.getBalance();
        this.cardType = account.getCardType();
        this.cardNumber = account.getCardNumber();
    }

    public Map<Long, String> getMapUser() {
        return mapUser;
    }

    public void setMapUser(Map<Long, String> mapUser) {
        this.mapUser = mapUser;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public Integer getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(Integer transactionCount) {
        this.transactionCount = transactionCount;
    }
}
