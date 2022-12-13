package movieticket;

import java.util.Set;

public class NormalTheatre extends Theatre {

    private String owner;

    public NormalTheatre() {
        super();
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
