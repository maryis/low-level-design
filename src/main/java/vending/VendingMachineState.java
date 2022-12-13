package vending;

import java.util.List;

public interface VendingMachineState {

    int dispenseChange(Product product);
    void addItem(Product product);
    void fill(List<Product> list);
    void dispenseItem(Product product);
    void collectMoney(Product product);
    void setVM(VendingMachine vm);
}
