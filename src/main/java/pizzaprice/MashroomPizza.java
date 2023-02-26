package pizzaprice;

public class MashroomPizza extends Pizza{

    public MashroomPizza(Size size) {
        super(size);
    }

    @Override
    int getPrice() {
        return price + 15;
    }
}
