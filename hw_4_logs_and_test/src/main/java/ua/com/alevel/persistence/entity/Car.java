package ua.com.alevel.persistence.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Car {

    private int yearOfIssue;
    private int serialNumber;
    private String manufacture;
    private String brand;
    private int idDrivers;

    @Override
    public String toString() {
        return "Car{" +
                "serial Number=" + serialNumber +
                ", manufacture='" + manufacture + '\'' +
                ", brand='" + brand + '\'' +
                ", yearsOfIssue=" + yearOfIssue +
                ", idDrivers=" + idDrivers +
                '}';
    }
}
