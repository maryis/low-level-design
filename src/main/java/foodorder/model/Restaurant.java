package foodorder.model;

import java.util.HashSet;
import java.util.Set;

public class Restaurant {
    private String name;
    private Food food;
    private int quantity;
    private Set<String> pinCodes;

    public Restaurant(String name, Food food, int quantity, Set<String> pinCodes) {
        this.name = name;
        this.food = food;
        this.quantity = quantity;
        this.pinCodes = pinCodes;
    }

    public Restaurant(String name, Food food, int quantity) {
        this(name, food, quantity, new HashSet<>());
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPinCodes(Set<String> pinCodes) {
        this.pinCodes = pinCodes;
    }

    public void addPinCodes(String pinCode) {
        this.pinCodes.add(pinCode);
    }


    public String getName() {
        return name;
    }

    public Food getFood() {
        return food;
    }

    public int getQuantity() {
        return quantity;
    }

    public Set<String> getPinCodes() {
        return pinCodes;
    }
}
