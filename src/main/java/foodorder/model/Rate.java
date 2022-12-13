package foodorder.model;

import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

public final class Rate {
    private UUID id;
    private Restaurant restaurant;
    private User user;
    private Date date;
    @Size(min=1, max=5)
    private int rate;
    private String comment;

    public Rate(Restaurant restaurant, User user, Date date, int rate, String comment) {
        this.restaurant = restaurant;
        this.user = user;
        this.date = date;
        this.rate = rate;
        this.comment = comment;
        this.id = UUID.randomUUID();
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public User getUser() {
        return user;
    }

    public Date getDate() {
        return date;
    }

    public int getRate() {
        return rate;
    }

    public String getComment() {
        return comment;
    }
}
