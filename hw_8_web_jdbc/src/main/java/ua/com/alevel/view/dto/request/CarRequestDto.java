package ua.com.alevel.view.dto.request;

public class CarRequestDto extends RequestDto{

    private String carName;
    private String color;
    private double engineCapacity;
    private Integer yearsOfIssue;
    private String imageUrl;
    private String carNumber;
    private Long driverId;

    public Long getDriverId() {
        return driverId;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

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
