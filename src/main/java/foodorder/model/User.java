package foodorder.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

public class User {
    @NotNull(message = "name should not be null")
    private String name;
    private GenderType gender;
    @Pattern(regexp = "^\\d{9}$" )
    private String phoneNumber;
    private UUID id;
    private int pinCode;

    public User(String name, GenderType gender, String phoneNumber, int pinCode) {
        this.name = name;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.pinCode = pinCode;
        this.id = UUID.randomUUID();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    public String getName() {
        return name;
    }

    public GenderType getGender() {
        return gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UUID getId() {
        return id;
    }

    public int getPinCode() {
        return pinCode;
    }
}
