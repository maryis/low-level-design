package pizzaprice;

import java.util.List;

public abstract class Pizza {
    Size size;
    List<Topping> toppings;
    int price;
    private static final int BASE_PRICE = 50;

    public Pizza(Size size) {
        price = BASE_PRICE * size.getPercent();
    }

    abstract int getPrice();

    void addTopping(Topping topping) {
        toppings.add(topping);
        price += topping.getPrice();
    }


}
