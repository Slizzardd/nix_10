package ua.com.alevel.view.dto.request;

public class CategoryRequestDto extends RequestDto{

    private String name;
    private boolean income;

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
}
