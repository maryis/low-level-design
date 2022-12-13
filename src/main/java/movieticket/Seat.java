package movieticket;

public class Seat {
    public enum Status{
        AVAILABLE,
        BOOKED
    }

    public Seat(Status status) {
        this.status = Status.AVAILABLE;
    }

    int row;
    int column;
    Theatre theatre;
    Status status;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
