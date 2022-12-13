package vending;

import java.util.List;

public class VendingMachine implements VendingMachineState {

    VendingMachineState state;
    List<Product> products;

    public VendingMachine() {
        setState(new ReadyState());
    }

    public void setState(VendingMachineState state) {
        this.state = state;
        this.state.setVM(this);
    }

    @Override
    public int dispenseChange(Product product) {
        return state.dispenseChange(product);
    }

    @Override
    public void addItem(Product product) {
        state.addItem(product);
    }

    public void fill(List<Product> list) {
        state.fill(list);
    }

    @Override
    public void dispenseItem(Product product) {
        state.dispenseItem(product);

    }

    @Override
    public void collectMoney(Product product) {
state.collectMoney(product);
    }

    @Override
    public void setVM(VendingMachine vm) {
    }


}
