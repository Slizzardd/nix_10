package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Category;

public class CategoryResponseDto extends ResponseDto{

    private String name;
    private boolean income;
    private Integer transactionCount;

    public CategoryResponseDto(Category category) {
        setId(category.getId());
        setCreated(category.getCreated());
        setUpdated(category.getUpdated());
        setVisible(category.getVisible());
        this.name = category.getName();
        this.income = category.getIncome();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIncome() {
        return income;
    }

    public void setIncome(boolean income) {
        this.income = income;
    }

    public Integer getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(Integer transactionCount) {
        this.transactionCount = transactionCount;
    }
}
