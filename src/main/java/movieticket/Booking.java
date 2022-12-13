package movieticket;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Booking { //sanse

    private Movie movie;
    private String fromHour;
    private String toHour;
    private Theatre theatre;
    private int price;

    public Booking(Movie movie, String fromHour, String toHour, Theatre theatre, int price) {
        this.movie = movie;
        this.fromHour = fromHour;
        this.toHour = toHour;
        this.theatre = theatre;
        this.price = price;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getFromHour() {
        return fromHour;
    }

    public void setFromHour(String fromHour) {
        this.fromHour = fromHour;
    }

    public String getToHour() {
        return toHour;
    }

    public void setToHour(String toHour) {
        this.toHour = toHour;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Set<Seat> getAllSeats(){
        return theatre.getSeats();
    }


    public synchronized boolean reserve(Seat seat){

        seat.setStatus(Seat.Status.BOOKED);
        return true;
    }

    public boolean cancel(Seat seat){

        seat.setStatus(Seat.Status.AVAILABLE);
        return true;
    }
}
