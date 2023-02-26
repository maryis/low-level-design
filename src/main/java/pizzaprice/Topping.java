package pizzaprice;

public enum  Topping {
    TOPP1(10),
    TOPP2(12);

    private int price;
    private Topping(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
