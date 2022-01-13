package ua.com.alevel.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    private String name;

    private boolean income; //true - +, false - -;

    @OneToMany(mappedBy = "category")
    private Set<Transaction> transactions;

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIncome() {
        return income;
    }

    public void setIncome(boolean finances) {
        this.income = finances;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Category() {
        super();
        this.transactions = new HashSet<>();
    }
}