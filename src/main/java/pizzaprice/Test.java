package pizzaprice;

import java.util.List;

public class Test {
}

abstract class Pizza1 {
    Size1 size;
    List<Topping1> toppings;
    int price;
    private static final int BASE_PRICE = 50;

    public Pizza1(Size1 size) {
        price = BASE_PRICE * size.getPercent();
    }

    abstract int getPrice();

    void addTopping(Topping1 topping) {
        toppings.add(topping);
        price += topping.getPrice();
    }
}
enum Topping1 {
    TOPP1(10),
    TOPP2(12);

    private int price;
    private Topping1(int price) {
        this.price = price;
    }
    public int getPrice() {
        return price;
    }
}

enum Size1 {
    SMALL(1),
    LARGE(15),
    MEDIUM(50);

    private int percent;
    private Size1(int percent) {
        this.percent = percent;
    }

    public int getPercent() {
        return percent;
    }
}

class CheesePizza1 extends Pizza1{
    public CheesePizza1(Size1 size) {
        super(size);
    }

    @Override
    int getPrice() {
        return price + 5;
    }
}
class MashroomPizza1 extends Pizza1{

    public MashroomPizza1(Size1 size) {
        super(size);
    }

    @Override
    int getPrice() {
        return price + 15;
    }
}
