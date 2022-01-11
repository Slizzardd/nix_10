package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Driver;

public class DriverResponseDto extends ResponseDto {

    private String firstName;
    private String lastName;
    private String notes;
    private double balance;
    private Integer carCount;
    private String imageUrl;

    public DriverResponseDto() {
    }

    public DriverResponseDto(Driver driver) {
        setId(driver.getId());
        setCreated(driver.getCreated());
        setUpdated(driver.getUpdated());
        setVisible(driver.getVisible());
        this.firstName = driver.getFirstName();
        this.lastName = driver.getLastName();
        this.notes = driver.getNotes();
        this.balance = driver.getBalance();
        this.imageUrl = driver.getImageUrl();

    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Integer getCarCount() {
        return carCount;
    }

    public void setCarCount(Integer carCount) {
        this.carCount = carCount;
    }
}
