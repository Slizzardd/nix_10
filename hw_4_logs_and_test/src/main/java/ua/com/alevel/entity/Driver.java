package ua.com.alevel.entity;

public class Driver {

    private String id;
    private String name;
    private int age;
    private String number;
    private String email;
    private Car[] car;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name ='" + name + '\'' +
                ", phone Number" + number +
                ", email='" + email + '\'' +
                '}' + "\n ";
    }
}
