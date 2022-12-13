package movieticket;

public class TheatreFactory {


    public enum Type {
        NORMAL,
        SPECIAL
    }

    Theatre getTheatre(Type type) {

        Theatre theatre = null;
        switch (type) {
            case NORMAL:
                theatre = new NormalTheatre();
            case SPECIAL:
                theatre = new SpecialTheatre();

        }
        return theatre;
    }
}
