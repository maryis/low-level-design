package foodorder.model;

import java.security.PrivateKey;
import java.util.Date;
import java.util.UUID;

public class Order {
    private UUID id;
    private Restaurant restaurant;
    private User user;
    private int quantity;
    private Date date;

    public Order(Restaurant restaurant, User user, int quantity, Date date) {
        this.restaurant = restaurant;
        this.user = user;
        this.quantity = quantity;
        this.date = date;
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public User getUser() {
        return user;
    }

    public int getQuantity() {
        return quantity;
    }

    public Date getDate() {
        return date;
    }
}
