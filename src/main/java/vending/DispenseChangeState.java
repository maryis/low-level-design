package vending;

import java.util.List;

public class DispenseChangeState implements VendingMachineState {
    private VendingMachine vendingMachine;

    public DispenseChangeState() {
    }

    @Override
    public void setVM(VendingMachine vm) {
        vendingMachine=vm;
    }

    @Override
    public int dispenseChange(Product product) {

        System.out.println("dispense change done");
        vendingMachine.setState(new DispenseItemState());
        System.out.println("going to dispense item mode");

        return 0;
    }

    @Override
    public void addItem(Product product) {
        throw new RuntimeException("unable to add item during dispense change");
    }

    @Override
    public void fill(List<Product> list) {
        throw new RuntimeException("unable to add item during dispense change");
    }

    @Override
    public void dispenseItem(Product product) {
        throw new RuntimeException("unable to dispense item during dispense change");

    }

    @Override
    public void collectMoney(Product product) {
        throw new RuntimeException("unable to collect money during dispense change");

    }
}
