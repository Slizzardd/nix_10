package ua.com.alevel.view.dto.request;

public class UserRequestDto extends RequestDto{

    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
    private String passport_INN;
    private String phoneNumber;
    private String accountName;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassport_INN() {
        return passport_INN;
    }

    public void setPassport_INN(String passport_INN) {
        this.passport_INN = passport_INN;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}
