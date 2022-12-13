package vending;

import java.util.List;

public class DispenseItemState implements VendingMachineState {
    private VendingMachine vendingMachine;

    @Override
    public void setVM(VendingMachine vm) {
        vendingMachine=vm;
    }
    @Override
    public int dispenseChange(Product product) {
        throw new RuntimeException("unable to dispense change during dispense item");

    }

    @Override
    public void addItem(Product product) {
        throw new RuntimeException("unable to add item during dispense item");

    }

    @Override
    public void fill(List<Product> list) {
        throw new RuntimeException("unable to add item during dispense item");

    }

    @Override
    public void dispenseItem(Product product) {
        System.out.println("dispense item done");
        vendingMachine.setState(new ReadyState());
        System.out.println("going to ready mode");

    }

    @Override
    public void collectMoney(Product product) {
        throw new RuntimeException("unable to collect money during dispense item");

    }
}
