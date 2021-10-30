package ua.com.alevel.entity;

public class Car {

    private String manufacturer;
    private String brand;
    private int age;
    private String serialNumber;
    private String idOwner;

    public String getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(String idOwner) {
        this.idOwner = idOwner;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        if(serialNumber == null){
            throw new RuntimeException("ВВЕДИТЕ СЕРИЙНЫЙ НОМЕР!!!");
        }else{
            this.serialNumber = serialNumber;
        }
    }
    @Override
    public String toString() {
        return "Car{" +
                "Manufactrurer='" + manufacturer + '\'' +
                ", brand ='" + brand + '\'' +
                ", serial number" + serialNumber +
                ", age='" + age + '\'' +
                ", idOwner='" + idOwner + '\'' +
                '}' + "\n ";
    }
}
