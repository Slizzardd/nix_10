package ua.com.alevel.persistence.entity;

import ua.com.alevel.persistence.type.CardType;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "accounts")
public class Account extends BaseEntity {

    @Column(name = "card_number")
    private String cardNumber;

    private Double balance;

    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "account", cascade = {
            CascadeType.REMOVE
    })
    private Set<Transaction> transactions;

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Account() {
        super();
        setCardNumber(UUID.randomUUID().toString());
    }
}