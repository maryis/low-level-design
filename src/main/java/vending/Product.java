package vending;

public class Product {

    private String code;
    private int price;
    private int collectedCash;

    public Product(String code, int price) {
        this.code = code;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCollectedCash() {
        return collectedCash;
    }

    public void setCollectedCash(int collectedCash) {
        this.collectedCash = collectedCash;
    }
}
