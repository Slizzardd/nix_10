package ua.com.alevel.view.dto.request;

public class CarRequestDto extends RequestDto{

    private String carName;
    private String color;
    private double engineCapacity;
    private Integer yearsOfIssue;
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(double engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public Integer getYearsOfIssue() {
        return yearsOfIssue;
    }

    public void setYearsOfIssue(Integer yearsOfIssue) {
        this.yearsOfIssue = yearsOfIssue;
    }
}
