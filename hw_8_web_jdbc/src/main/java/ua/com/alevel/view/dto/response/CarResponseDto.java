package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Car;

public class CarResponseDto extends ResponseDto {

    private String carName;
    private String color;
    private double engineCapacity;
    private Integer yearsOfIssue;
    private String imageUrl;

    public CarResponseDto() {
    }

    public CarResponseDto(Car car) {
        this();
        setId(car.getId());
        setCreated(car.getCreated());
        setUpdated(car.getUpdated());
        setVisible(car.getVisible());
        this.carName = car.getCarName();
        this.imageUrl = car.getImageUrl();
        this.color = car.getColor();
        this.engineCapacity = car.getEngineCapacity();
        this.yearsOfIssue = car.getYearsOfIssue();

    }

    public String getCarName() {
        return carName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
