package pizzaprice;

public class CheesePizza extends Pizza{

    public CheesePizza(Size size) {
        super(size);
    }

    @Override
    int getPrice() {
        return price + 5;
    }
}
