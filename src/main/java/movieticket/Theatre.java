package movieticket;

import java.util.Set;

public abstract class Theatre {


    private Set<Seat> seats;
    private String address;

    public Set<Seat> getSeats() {
        return seats;
    }

    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void fill(Set<Seat> seats){
        seats=seats;
    }
}
