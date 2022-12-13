package movieticket;

import java.util.Optional;

public class Ticket {

    private int refNo;
    private Booking booking;
    private Seat seat;
    private User user;

    public Ticket(Booking booking, Seat seat, User user) {
        this.booking = booking;
        this.seat = seat;
        this.user = user;
    }

    public int getRefNo() {
        return refNo;
    }

    public void setRefNo(int refNo) {
        this.refNo = refNo;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public Optional<Ticket> buy(Booking booking, User user, Seat seat){
        int id;
        Ticket t = null;
        if(booking.reserve(seat)){
            id=12;//new unique id from db/jpa
            Ticket ticket=new Ticket(booking,seat,user);
            ticket.refNo=12;
            
        }
        return Optional.ofNullable(t);
            
    }
}
