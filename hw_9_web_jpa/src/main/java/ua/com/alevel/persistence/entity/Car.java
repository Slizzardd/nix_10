package ua.com.alevel.persistence.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Entity
@Transactional
public class Car extends BaseEntity {

    @Column(name = "cars_name")
    private String carName;

    private String color;

    @Column(name = "engine_of_capacity")
    private double engineCapacity;

    @Column(name = "years_of_issue")
    private Integer yearsOfIssue;

    @Column(name = "car_number")
    private String carNumber;

    @ManyToMany(mappedBy = "cars", cascade = CascadeType.ALL)
    private Set<Driver> drivers;

    public Car() {
        super();
        drivers = new HashSet<>();
    }

    public Set<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(Set<Driver> drivers) {
        this.drivers = drivers;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
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
