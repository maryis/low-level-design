package vending;

import java.util.List;

public class FillingState implements VendingMachineState {

    private VendingMachine vendingMachine;

    @Override
    public void setVM(VendingMachine vm) {
        vendingMachine=vm;
    }

    @Override
    public int dispenseChange(Product product) {
        throw new RuntimeException("unable to dispense change during filling");
    }

    @Override
    public void addItem(Product product) {
        System.out.println("add item done");
        vendingMachine.products.add(product);
        System.out.println("going to ready mode");
        vendingMachine.setState(new ReadyState());
    }

    @Override
    public void fill(List<Product> list) {
        vendingMachine.products = list;
        System.out.println("add list done");
        System.out.println("going to ready mode");
        vendingMachine.setState(new ReadyState());
    }

    @Override
    public void dispenseItem(Product product) {
        throw new RuntimeException("unable to dispense item during filling");

    }

    @Override
    public void collectMoney(Product product) {
        throw new RuntimeException("unable to collect money during filling");

    }
}
