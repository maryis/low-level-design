package vending;

import java.util.List;

public class ReadyState implements VendingMachineState {

    private VendingMachine vendingMachine;

    @Override
    public void setVM(VendingMachine vm) {
        vendingMachine=vm;
    }

    @Override
    public int dispenseChange(Product product) {
        throw new RuntimeException("unable to dispense change during ready");
    }

    @Override
    public void addItem(Product product) {
        throw new RuntimeException("unable to add item during ready");

    }

    @Override
    public void fill(List<Product> list) {
        throw new RuntimeException("unable to add item during ready");

    }

    @Override
    public void dispenseItem(Product product) {
        throw new RuntimeException("unable to add item during ready");

    }

    @Override
    public void collectMoney(Product product) {
        System.out.println("collect money done");
        if(1==1) {//money is more
            vendingMachine.setState(new DispenseChangeState());
            System.out.println("going to dispense change mode");
        }
        else {
            vendingMachine.setState(new DispenseItemState());
            System.out.println("going to dispense item mode");
        }
        }
}
