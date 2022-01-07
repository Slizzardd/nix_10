package ua.com.alevel.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.List.MyList;

@Getter
@Setter
public class Driver extends Car {

    private int id;
    private String name;
    private String phoneNumber;
    private MyList<Car> car;

    @Override
    public String toString() {
        return "Driver{" +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", phone Number'" + phoneNumber + '\'' +
                '}';
    }
}
